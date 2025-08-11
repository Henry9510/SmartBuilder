 (function () {
            "use strict";

            // === Config ===
            const API_BASE = "http://localhost:8080/api"; // ajusta si es necesario
            const endpoints = {
                venturi: `${API_BASE}/venturi`,
                chasis: `${API_BASE}/chasis`,
                filtros: `${API_BASE}/filtros`,
                materiales: `${API_BASE}/materiales`, // esperado (GET con query params)
            };

            // === DOM ===
            const ui = {
                venturi: document.getElementById('capacidadVenturi'),
                chasis: document.getElementById('tipoVehiculo'),
                filtroTipo: document.getElementById('tipoFiltro'),
                filtroCap: document.getElementById('capacidadFiltro'),
                carretelAcc: document.getElementById('accionamientoCarretel'),
                carretelCap: document.getElementById('capacidadCarretel'),
                tanqueCap: document.getElementById('capacidadTanque'),
                generar: document.getElementById('btnGenerarMateriales'),
                retry: document.getElementById('btnReintentar'),
                estado: document.getElementById('estadoCarga'),
                tbody: document.querySelector('#tablaInsumos tbody'),
            };

            // === Estado ===
            let listaVenturis = [];
            let listaChasis = [];
            let listaFiltros = [];

            // === Helpers ===
            const controller = new AbortController();

            function setLoading(msg) { ui.estado.textContent = msg || ''; }
            function showRetry(show) { ui.retry.hidden = !show; }

            function hasValue(sel) { return !!sel.value; } // '' = no, 'NA' cuenta como valor

            function enableSelect(sel, enabled, { clear = true } = {}) {
                sel.disabled = !enabled;
                if (!enabled && clear) { sel.value = ''; }
            }

            async function safeFetch(url) {
                const res = await fetch(url, { signal: controller.signal });
                if (!res.ok) throw new Error(`${res.status} ${res.statusText}`);
                return res.json();
            }

            function buildOptions(selectEl, data, { valueKey, labelKey, placeholder }) {
                selectEl.innerHTML = '';
                const frag = document.createDocumentFragment();
                const def = document.createElement('option');
                def.value = ''; def.textContent = placeholder || 'Seleccionar';
                const na = document.createElement('option');
                na.value = 'NA'; na.textContent = 'No aplica';
                frag.appendChild(def); frag.appendChild(na);
                for (const item of data) {
                    const o = document.createElement('option');
                    o.value = item[valueKey];
                    o.textContent = item[labelKey];
                    frag.appendChild(o);
                }
                selectEl.appendChild(frag);
            }

            function cascade() {
                // Paso 1 -> Paso 2 (Tanque)
                enableSelect(ui.tanqueCap, hasValue(ui.chasis));

                // Paso 2 -> Filtro (tipo)
                const step2Done = hasValue(ui.tanqueCap);
                enableSelect(ui.filtroTipo, step2Done);

                // Filtro tipo -> Filtro capacidad (si es NA, auto-fijar y bloquear)
                if (ui.filtroTipo.value === 'NA') {
                    ui.filtroCap.value = 'NA';
                    enableSelect(ui.filtroCap, false, { clear: false });
                } else {
                    enableSelect(ui.filtroCap, hasValue(ui.filtroTipo));
                }
                const filtroDone = hasValue(ui.filtroTipo) && (ui.filtroTipo.value === 'NA' || hasValue(ui.filtroCap));

                // Filtro -> Carretel (accionamiento)
                enableSelect(ui.carretelAcc, filtroDone);
                // Accionamiento -> Capacidad
                if (ui.carretelAcc.value === 'NA') {
                    ui.carretelCap.value = 'NA';
                    enableSelect(ui.carretelCap, false, { clear: false });
                } else {
                    enableSelect(ui.carretelCap, hasValue(ui.carretelAcc));
                }
                const carretelDone = hasValue(ui.carretelAcc) && (ui.carretelAcc.value === 'NA' || hasValue(ui.carretelCap));

                // Carretel -> Venturi
                enableSelect(ui.venturi, carretelDone);

                // Listo para generar: último paso con valor (incluye NA)
                ui.generar.disabled = !hasValue(ui.venturi);
            }

            function fillTable(rows) {
                ui.tbody.innerHTML = '';
                if (!rows || !rows.length) {
                    const tr = document.createElement('tr');
                    const td = document.createElement('td');
                    td.colSpan = 10; td.textContent = 'Sin resultados para los criterios seleccionados.';
                    tr.appendChild(td); ui.tbody.appendChild(tr);
                    return;
                }
                const frag = document.createDocumentFragment();
                for (const r of rows) {
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
            <td>${r.ubicacion ?? '-'}</td>
            <td>${r.codigo ?? '-'}</td>
            <td>${r.descripcion ?? '-'}</td>
            <td>${r.numeroParte ?? '-'}</td>
            <td>${r.procedencia ?? '-'}</td>
            <td>${r.unidad ?? '-'}</td>
            <td>${r.categoria ?? '-'}</td>
            <td>${r.stock ?? 0}</td>
            <td><button class="btn-row" data-action="ver" data-id="${r.id ?? ''}">Ver</button></td>
            <td><button class="btn-row" data-action="agregar" data-id="${r.id ?? ''}">Agregar</button></td>
          `;
                    frag.appendChild(tr);
                }
                ui.tbody.appendChild(frag);
            }

            // === Carga inicial ===
            async function init() {
                setLoading('Cargando datos iniciales…');
                showRetry(false); ui.generar.disabled = true;
                try {
                    const [venturis, chasis, filtros] = await Promise.all([
                        safeFetch(endpoints.venturi),
                        safeFetch(endpoints.chasis),
                        safeFetch(endpoints.filtros),
                    ]);
                    listaVenturis = venturis; listaChasis = chasis; listaFiltros = filtros;

                    buildOptions(ui.chasis, listaChasis, { valueKey: 'id', labelKey: 'nombre', placeholder: 'Seleccionar tipo de chasis' });
                    buildOptions(ui.filtroTipo, listaFiltros, { valueKey: 'codigoFiltro', labelKey: 'nombre', placeholder: 'Seleccionar tipo de filtro' });
                    buildOptions(ui.venturi, listaVenturis, { valueKey: 'codigo', labelKey: 'descripcion', placeholder: 'Seleccionar venturi' });

                    // Paso inicial: sólo chasis activo, demás bloqueados (ya vienen disabled en HTML)
                    cascade();
                    setLoading('Listo.');
                    setTimeout(() => setLoading(''), 1200);
                } catch (err) {
                    console.error(err);
                    setLoading('No se pudo cargar. Revisa el backend o CORS.');
                    showRetry(true);
                }
            }

            // === Generar lista ===
            async function generarLista() {
                const params = new URLSearchParams({
                    chasisId: ui.chasis.value,
                    capacidadTanque: ui.tanqueCap.value,
                    filtroCodigo: ui.filtroTipo.value,
                    filtroCapacidad: ui.filtroCap.value,
                    carretelAccionamiento: ui.carretelAcc.value,
                    carretelCapacidad: ui.carretelCap.value,
                    venturiCodigo: ui.venturi.value,
                });
                try {
                    setLoading('Generando lista de materiales…');
                    ui.generar.disabled = true;
                    const res = await fetch(`${endpoints.materiales}?${params.toString()}`);
                    if (!res.ok) throw new Error(`${res.status} ${res.statusText}`);
                    const rows = await res.json();
                    fillTable(rows);
                } catch (err) {
                    console.error(err);
                    fillTable([]);
                    alert('No fue posible generar la lista. Verifica el endpoint /materiales.');
                } finally {
                    setLoading('');
                    cascade();
                }
            }

            // === Listeners (cascada) ===
            ['change', 'keyup'].forEach(ev => {
                ui.chasis.addEventListener(ev, cascade);
                ui.tanqueCap.addEventListener(ev, cascade);
                ui.filtroTipo.addEventListener(ev, cascade);
                ui.filtroCap.addEventListener(ev, cascade);
                ui.carretelAcc.addEventListener(ev, cascade);
                ui.carretelCap.addEventListener(ev, cascade);
                ui.venturi.addEventListener(ev, cascade);
            });

            ui.retry.addEventListener('click', init);
            ui.generar.addEventListener('click', generarLista);

            window.addEventListener('beforeunload', () => controller.abort());

            // Arranque
            init();
        })();
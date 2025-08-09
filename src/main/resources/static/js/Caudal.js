function calcularCaudalHagenPoiseuille(d_mm, deltaP_Pa, mu_Pa_s, longitud_m) {
    // Convierte diámetro a metros
    const d_m = d_mm / 1000;

    // Constantes y fórmula: Q = (π * ΔP * d^4) / (128 * μ * L)
    const numerador = Math.PI * deltaP_Pa * Math.pow(d_m, 4);
    const denominador = 128 * mu_Pa_s * longitud_m;

    const Q_m3_s = numerador / denominador; // Caudal en m³/s

    // También podemos devolver en litros por segundo
    const Q_L_s = Q_m3_s * 1000;

    return {
        caudal_m3_s: Q_m3_s,
        caudal_L_s: Q_L_s
    };
}

// Ejemplo de uso:
const diametro_mm = 10;        // diámetro interno del tubo (mm)
const deltaP = 50000;          // diferencia de presión (Pa)
const viscosidad = 0.001;      // viscosidad dinámica (Pa·s) - agua a 20°C
const longitud = 2;            // longitud del tubo (m)

const resultado = calcularCaudalHagenPoiseuille(diametro_mm, deltaP, viscosidad, longitud);

console.log(`Caudal: ${resultado.caudal_m3_s.toFixed(6)} m³/s`);
console.log(`Caudal: ${resultado.caudal_L_s.toFixed(3)} L/s`);

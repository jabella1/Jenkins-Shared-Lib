def call(String nombreEtapa = null, String buildNumber = null, Closure bloque) {
    def etapa = nombreEtapa ?: (env.STAGE_NAME ?: "etapa_desconocida")
    def build = buildNumber ?: (env.BUILD_NUMBER ?: "build_desconocido")
    try {
        bloque()
    } catch (Exception e) {
        def mensajeError = "Error en etapa ${etapa}:\n${e.getMessage()}"
        env.LOG_FILE = "error-${etapa.replace(' ', '_')}-${build}.log"
        writeFile file: "${env.LOG_FILE}", text: mensajeError
        currentBuild.result = 'FAILURE'
        error("Falló la etapa ${etapa}")
    }
}

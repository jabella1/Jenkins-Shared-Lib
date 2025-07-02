def call(String nombreEtapa = null, Closure bloque) {
    def etapa = nombreEtapa ?: (env.STAGE_NAME ?: "etapa_desconocida")
    try {
        bloque()
    } catch (Exception e) {
        def mensajeError = "Error en etapa ${etapa}:\n${e.getMessage()}"
        writeFile file: "${env.LOG_FILE}", text: mensajeError
        currentBuild.result = 'FAILURE'
        error("Falló la etapa ${etapa}")
    }
}


def call(Map params) {

    def EMAILS_ERROR = [
        'jabella@nexura.com.co'
    ].join(',')

    def etapa = params.nombreEtapa ?: (env.STAGE_NAME ?: "etapa_desconocida")
    def job = params.nombreJob ?: (env.JOB_NAME ?: "job_desconocido")
    def build = params.buildNumero ?: (env.BUILD_NUMBER ?: "build_desconocido")
    def fechaHora = new Date().format("yyyy-MM-dd HH:mm")

    def mensaje = """
        <p>Ocurrió un fallo en el pipeline de Jenkins.</p>

        <ul>
          <li><strong>Job:</strong> ${job}</li>
          <li><strong>Etapa:</strong> ${etapa}</li>
          <li><strong>Build #:</strong> ${build}</li>
          <li><strong>Fecha y hora:</strong> ${fechaHora}</li>
        </ul>

        <p>Se adjunta el log con detalles del error.</p>
    """

     emailext(
        to: EMAILS_ERROR,
        subject: "ERROR - Etapa: ${etapa} | Job: ${job} | Build #${build}",
        body: mensaje,
        attachmentsPattern: "${env.LOG_FILE}",
        mimeType: 'text/html')
}
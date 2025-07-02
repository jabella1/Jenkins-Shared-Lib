static final String EMAILS_ERROR = [
    'jabella@nexura.com.co',
].join(',')

def call(Map params) {
    
    def etapa = params.nombreEtapa ?: (script.env.STAGE_NAME ?: "etapa_desconocida")
    def job = params.nombreJob ?: (script.env.JOB_NAME ?: "job_desconocido")
    def build = params.buildNumero ?: (script.env.BUILD_NUMBER ?: "build_desconocido")
    def fechaHora = new Date().format("yyyy-MM-dd HH:mm"

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
        subject: "ERROR - Etapa: ${env.STAGE_NAME} | Job: ${env.JOB_NAME} | Build #${env.BUILD_NUMBER}",
        body: mensaje,
        attachmentsPattern: "${env.LOG_FILE}")
    )
}
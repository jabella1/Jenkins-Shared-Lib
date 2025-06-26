def call(Map params) {

    if (!params?.repositorio) {
        error("Falta el parámetro obligatorio: 'repositorio'")
    }
    
    if (!params?.sha) {
        error("Falta el parámetro obligatorio: 'sha'")
    }

    def repositorio = params.repositorio
    def sha = params.sha  

    def fechaHora = new Date().format("yyyy-MM-dd HH:mm")
    def remitente = params.remitente ?: "Equipo DevOps"

    def mensaje = """
        <p>Les informamos que se ha realizado exitosamente la subida de la nueva imagen a DockerHub, la cual será utilizada en el pipeline de Jenkins.</p>

        <ul>
          <li><strong>Repositorio:</strong> ${repositorio}</li>
          <li><strong>SHA:</strong> ${sha}</li>
          <li><strong>Fecha y hora de carga:</strong> ${fechaHora}</li>
          <li><strong>Descripción:</strong> Imagen actualizada con los últimos cambios requeridos para el proceso de integración continua.</li>
        </ul>

        <p>Quedamos atentos a cualquier observación.</p>

        <p>Saludos cordiales,<br/>
        ${remitente}<br/>
        </p> 
    """

    emailext(
        to: env.DEFAULT_RECIPIENTS,
        subject: "Imagen Docker actualizada - ${repositorio}",
        body: mensaje,
        mimeType: 'text/html'
    )
}
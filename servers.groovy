def deploy(id) {
    unstash 'war'
    sh "cp x.war /Users/amuniz/jw/tomcat/webapps/${id}.war"
}

def undeploy(id) {
    sh "rm /Users/amuniz/jw/tomcat/webapps/${id}.war"
}

def runWithServer(body) {
    def id = UUID.randomUUID().toString()
    deploy id
    sleep 5
    try {
        body.call id
    } finally {
        undeploy id
    }
}

this

jettyUrl = 'http://localhost:8081/'

def servers

stage 'Dev'
node {
    checkout scm
    servers = load 'servers.groovy'
    //mvn '-o clean package'
    echo 'This is from feature one branch'
    echo 'mvn build here'
    //dir('target') {stash name: 'war', includes: 'x.war'}
}

stage 'QA'
node {
    //runTests(servers, 30)
    echo 'run test here'
}

node {
    echo "deploy here"
  //  servers.deploy 'staging'
}

//input message: "Does ${jettyUrl}staging/ look good?"
input message: 'Does ${jettyUrl}staging/ look good?', ok: 'approval', parameters: [booleanParam(defaultValue: true, description: '', name: 'deployed')], submitter: 'admin'


//stage name: 'Production', concurrency: 1
node {
    //servers.deploy 'production'
    echo "Deployed to ${jettyUrl}production/"
}

def mvn(args) {
    sh "${tool 'maven_3_5_0'}/bin/mvn ${args}"
}

def runTests(servers, duration) {
    node {
        checkout scm
        servers.runWithServer {id ->
            mvn "-o -f sometests test -Durl=${jettyUrl}${id}/ -Dduration=${duration}"
        }
    }
}

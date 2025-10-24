pipeline {
    agent any

    environment {
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-21"
        PATH = "${env.JAVA_HOME}\\bin;C:\\Maven\\apache-maven-3.9.10\\bin;${env.PATH}"
        APP_PORT = "9090"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/yuvraj1-github/3tier-app.git'
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Application') {
            steps {
                bat "taskkill /F /IM java.exe /T || echo No running Java process"
                bat "start cmd /c java -jar target/demo-0.0.1-SNAPSHOT.jar --server.port=%APP_PORT%"
            }
        }
    }
}

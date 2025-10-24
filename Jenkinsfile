pipeline {
    agent any

    environment {
        APP_PORT = "9090"
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Pulling latest code from GitHub...'
                git branch: 'main', url: 'https://github.com/yuvraj1-github/3tier-app.git'
            }
        }

        stage('Build with Maven') {
            steps {
                echo 'Building the application using Maven...'
                dir('Backend/demo') {  // <-- folder where pom.xml is
                    bat 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Run Application') {
            steps {
                echo 'Running Spring Boot Application...'
                // Kill any existing Java process (Windows)
                bat "taskkill /F /IM java.exe /T || echo No running Java process"
                // Start the Spring Boot app
                bat "start cmd /c java -jar Backend\\demo\\target\\demo-0.0.1-SNAPSHOT.jar --server.port=%APP_PORT%"
            }
        }
    }

    post {
        success {
            echo '✅ Application built and deployed successfully!'
        }
        failure {
            echo '❌ Build or deployment failed. Please check logs.'
        }
    }
}

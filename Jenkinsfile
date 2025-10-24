pipeline {
    agent any

    tools {
        maven 'Maven' // Name must match Maven installation in Jenkins
        jdk 'JDK17'   // Name must match JDK installation in Jenkins
    }

    environment {
        // Optional: customize your application port
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
                sh './mvnw clean install -DskipTests'
            }
        }

        stage('Run Application') {
            steps {
                echo 'Running Spring Boot Application...'
                // Kill old instance if running
                sh 'pkill -f "demo-0.0.1-SNAPSHOT.jar" || true'
                // Start new instance
                sh 'nohup java -jar target/demo-0.0.1-SNAPSHOT.jar --server.port=${APP_PORT} > app.log 2>&1 &'
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

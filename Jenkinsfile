pipeline {
    agent any

    environment {
        BACKEND_PORT = "9090"
        FRONTEND_PORT = "8080"
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Pulling latest code from GitHub...'
                git branch: 'main', url: 'https://github.com/yuvraj1-github/3tier-app.git'
            }
        }

        stage('Build Backend with Maven') {
            steps {
                echo 'Building Spring Boot backend...'
                dir('backend') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Run Backend') {
            steps {
                echo "Starting Spring Boot backend on port ${BACKEND_PORT}..."
                dir('backend') {
                    bat "start cmd /c \"mvn spring-boot:run\""
                }
            }
        }

        stage('Wait for Backend') {
            steps {
                echo "Waiting 10 seconds for backend to start..."
                sleep 10
            }
        }

        stage('Serve Frontend') {
            steps {
                echo "Serving frontend on port ${FRONTEND_PORT}..."
                dir('frontend') {
                    bat "start cmd /c \"python -m http.server ${FRONTEND_PORT}\""
                }
            }
        }

        stage('Open Frontend in Browser') {
            steps {
                echo 'Opening frontend in default browser...'
                bat "start http://localhost:${FRONTEND_PORT}"
            }
        }
    }
}

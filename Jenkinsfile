pipeline {
    agent any

    environment {
        // Setup PATH
        PATH = "${env.PATH}:/usr/local/bin"
    }

    stages {
//         stage('Checkout') {
//             steps {
//                 //
//                 checkout scm
//             }
//         }

        stage('Start Docker Compose') {
            steps {
                script {
                    // run docker-compose up
                    sh 'docker-compose up -d' // -d là để chạy container ở chế độ background
                }
            }
        }

        stage('Run Maven Tests') {
            steps {
                script {
                    // run test with Maven
                    sh 'mvn clean test -DsuiteXmlFile=GoogleSearchTest_RemoteDocker_Parallel.xml'
                }
            }
        }

        stage('Shutdown Docker Compose') {
            steps {
                script {
                    // stop container
                    sh 'docker-compose down'
                }
            }
        }
    }

    post {
        always {
            // Cleanup
            echo 'Pipeline finished.'
        }
    }
}
pipeline {
    agent any

    environment {
        // Đảm bảo rằng /usr/local/bin được thêm vào biến môi trường PATH
        PATH = "${env.PATH}:/usr/local/bin"
    }

    stages {
//         stage('Checkout') {
//             steps {
//                 // Lấy mã nguồn từ Git (hoặc các hệ thống SCM khác)
//                 checkout scm
//             }
//         }

        stage('Start Docker Compose') {
            steps {
                script {
                    // Chạy docker-compose up
                    sh 'docker-compose up -d' // -d là để chạy container ở chế độ background
                }
            }
        }

        stage('Run Maven Tests') {
            steps {
                script {
                    // Chạy các bài kiểm tra Maven
                    sh 'mvn clean test -DsuiteXmlFile=GoogleSearchTest_RemoteDocker_Parallel.xml'
'
                }
            }
        }

        stage('Shutdown Docker Compose') {
            steps {
                script {
                    // Dừng các container sau khi kiểm tra xong
                    sh 'docker-compose down'
                }
            }
        }
    }

    post {
        always {
            // Cleanup hoặc các hành động sau khi pipeline kết thúc
            echo 'Pipeline finished.'
        }
    }
}
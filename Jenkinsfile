
pipeline {
    agent any

    tools {
    jdk 'JDK1'
    maven 'MAVEN3'
}

    stages {
        stage('Checkout') {
            steps {
                echo 'Source code is checked out from GitHub'
            }
        }

        stage('Verify Java and Maven') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run TestNG Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit testResults: '**/target/surefire-reports/*.xml',
                  allowEmptyResults: true
        }
    }
}

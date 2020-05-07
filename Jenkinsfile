pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /home/robbie/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -pl basic -DskipTests clean package'
            }
        }
    }
}
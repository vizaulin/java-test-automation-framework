pipeline {
    agent any

    tools {
        maven 'maven3.9.11'
    }

    parameters {
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Launch tests using headless mode?')
    }

    environment {
        ALLURE_RESULTS = 'allure-results'
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'helpers', url: 'git@github.com:vizaulin/java-test-automation-framework.git', credentialsId: 'testAutomationIdGit'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile -DskipTests'
            }
        }

        stage('Start Selenium Grid') {
            steps {
                sh "docker-compose -f ${DOCKER_COMPOSE_FILE} up -d --scale chrome=3"
            }
        }

        stage('Run Tests') {
            options { timeout(time: 5, unit: 'MINUTES') }
            steps {
                withCredentials([file(credentialsId: 'db_properties_file', variable: 'CONFIG_FILE')]) {
                sh "mvn clean test -DrunType=remote -Dheadless=${params.HEADLESS}"
            }
        }
    }

        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: "${ALLURE_RESULTS}"]]
            }
        }
    }

    post {
        always {
            echo 'Build completed'
            sh "docker-compose -f ${DOCKER_COMPOSE_FILE} down"
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Tests passed!'
        }
        failure {
            echo 'Tests failed!'
        }
    }
}
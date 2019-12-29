#!/usr/bin/env groovy

pipeline {

    agent any

    tools {
        maven 'M3'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr:'3'))
        timeout(time: 1, unit: 'HOURS')
    }

    environment {
        GITHUB_CREDENTIALS = '13e24678-e7ba-4618-961e-667ea5e4d1ac'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'dcd5063e-2081-4fd7-be07-9a235c5ca320', url: 'https://github.com/faboulaye/appInDevOps.git'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean -B compile'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Integration test') {
            steps {
                sh 'mvn failsafe:integration-test'
                junit '**/target/failsafe-reports/*.xml'
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploy on S3"
            }
        }

        stage('Delivery') {
            steps {
                echo "Deploy on tomcat"
            }
        }
    }
}

// vim: ft=groovy

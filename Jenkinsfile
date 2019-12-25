#!/usr/bin/env groovy

pipeline {

    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr:'3'))
        timeout(time: 1, unit: 'HOURS')
    }

    tools {
        maven 'M3'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: '4b296352-0ec4-48ad-aa9c-863188e1aaf0', url: 'https://gitlab.com/faboulaye/community-business.git'
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
                echo "Deploy"
            }
        }
    }
}

// vim: ft=groovy

#!/usr/bin/env groovy
pipeline {
    agent any
    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }
    environment {
        HOME="${env.WORKSPACE}"
        TMP="${env.WORKSPACE}/TMP"
    }
    stages {
        stage('Unit test') {
          steps {
              script {
                  sh 'echo "Udfører statisk kodeanalyse og unit tests..."'
                  sh './gradlew clean check --no-build-cache'
              }
          }
        }
        stage('Integration test') {
          steps {
            lock('wiremock-ports') {
              script {
                sh 'echo "Udfører integrations-tests..."'
                sh './gradlew integrationTest'
              }
            }
          }
          post {
            always {
              junit keepLongStdio: true, testResults: '**/**/build/test-results/integrationTest/*.xml'
            }
          }
        }
        stage('Byg og upload images') {
            steps {
                script {
                    sh 'npm install vite @vitejs/plugin-vue skat-bootstrap moment-timezone vuex@next axios --registry https://artifactory.ccta.dk/api/npm/ekapital-npm-virtual/'
                    sh './gradlew jib -Djib.console=plain --info'
                }
            }

        }
   }
}


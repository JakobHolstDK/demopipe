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
        stage('Stage 1') {
          steps {
              script {
                  sh 'echo "Udfører stage 1"'
                  sh './gradlew stage1'
              }
          }
        }
        stage('Stage 2') {  
        agent {
          kubernetes {
            defaultContainer 'kaniko'
            yaml '''
kind: Pod
spec:
  containers:
  - name: kaniko
    image: gcr.io/kaniko-project/executor:debug
    imagePullPolicy: Always
    command:
    - sleep
    args:
    - 99d
    - name: docker-registry-config
      configMap:
        name: docker-registry-config
'''
            }
          }
          steps {
              script {
                  sh 'echo "Udfører stage 2"'
                  sh './gradlew stage2'
              }
          }
        }
        stage('Stage 3') {
          steps {
              script {
                  sh 'echo "Udfører stage 3"'
                  sh './gradlew stage3'
              }
          }
        }
        stage('Stage 4') {
          steps {
              script {
                  sh 'echo "Udfører stage 4"'
                  sh './gradlew stage4'
              }
          }
        }
   }
}


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
            defaultContainer 'demodocker'
            yaml '''
kind: Pod
spec:
  containers:
  - name: demodocker
    image: registry.openknowit.com/demodocker:latest
    imagePullPolicy: Always
    command:
    - sleep
    args:
    - 60s 
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


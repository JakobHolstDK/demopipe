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
            defaultContainer 'dockerdemo'
            yaml '''
kind: Pod
spec:
  imagePullSecrets:
   - name: registry.openknowit.com
  containers:
  - name: demodocker
    image: registry.openknowit.com/dockerdemo:latest
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
        agent {
          kubernetes {
            defaultContainer 'dockerdemo'
            }
         }
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


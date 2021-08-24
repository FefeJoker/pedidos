#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('permissions setup'){
            steps{
                sh 'chmod 777 -R /var/lib/jenkins/workspace/Microservicio_de_Pedidos_develop'
            }
        }
        stage('maven clean build') {
            when {
                branch 'develop'
            }
            steps {
                sh "java -version"
                sh "./mvnw clean"
                sh "mvn clean install"
                sh "mvn clean package spring-boot:repackage"
                sh "echo buildeando develop"
            }
        }
        stage('dockerize') {
            steps {
                sh "docker build -t guillegregoret/pedidos ."
                sh 'docker ps -f name=pedidos-service -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=pedidos-service -q | xargs -r docker container rm'
                sh "docker run -d --name pedidos-service -p 9003:9003 guillegregoret/pedidos"
            }
        }
        /*stage('analisis estatico') {
            steps {
                sh "./mvnw site"
                sh "./mvnw checkstyle:checkstyle pmd:pmd pmd:cpd findbugs:findbugs spotbugs:spotbugs"
            }
        }*/
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
}
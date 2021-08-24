#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('permissions setup'){
            steps{
                sh 'chmod 777 -R /var/lib/jenkins/workspace/Microservicio_de_Cuentas_develop'
            }
        }
        stage('clean') {
            when {
                branch 'master'
            }
            steps {
                sh "java -version"
                sh "./mvnw clean"
            }
        }
        stage('clean-develop') {
            when {
                branch 'develop'
            }
            steps {
                sh "java -version"
                sh "./mvnw clean"
                sh "echo buildeando develop"
            }
        }
        stage('dockershit') {
            steps {
                sh "mvn clean install"
                sh "mvn clean package spring-boot:repackage"
                sh "docker build -t guillegregoret/cuentas ."
                sh 'docker ps -f name=cuentas-service -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=cuentas-service -q | xargs -r docker container rm'
                sh "docker run -d --name cuentas-service -p 9005:9005 guillegregoret/cuentas"
            }
        }
        /*stage('analisis estatico') {
            steps {
                sh "./mvnw site"
                sh "./mvnw checkstyle:checkstyle pmd:pmd pmd:cpd findbugs:findbugs spotbugs:spotbugs"
            }
        }*/
    }
    post {
        success{
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
        always{
            archiveArtifacts artifacts: '**/target/site/**', fingerprint: true
            publishHTML([allowMissing: false,
                         alwaysLinkToLastBuild: true,
                         keepAll: true,
                         reportDir: 'target/site',
                         reportFiles: 'index.html',
                         reportName: 'Site'
            ])
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
            jacoco ( execPattern: 'target/jacoco.exec')
            recordIssues enabledForFailure: true, tools: [mavenConsole(), java(), javaDoc()]
            recordIssues enabledForFailure: true, tools: [checkStyle()]
            recordIssues enabledForFailure: true, tools: [spotBugs()]
            recordIssues enabledForFailure: true, tools: [cpd(pattern: '**/target/cpd.xml')]
            recordIssues enabledForFailure: true, tools: [pmdParser(pattern: '**/target/pmd.xml')]
        }
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
}
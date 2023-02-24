sourceSets {
    integrationTest {
        groovy.srcDir "$projectDir/src/integration-test/groovy"
        resources.srcDir "$projectDir/src/integration-test/resources"
        compileClasspath += sourceSets.main.output + sourceSets.test.output
        runtimeClasspath += sourceSets.main.output + sourceSets.test.output
    }
}

configurations {
    integrationTestImplementation.extendsFrom testImplementation
    integrationTestRuntimeOnly.extendsFrom testRuntimeOnly
    integrationTestApi.extendsFrom testApi
}

task integrationTest(type: Test) {
    group = 'verification'
    testClassesDirs = sourceSets.integrationTest.output.classesDirs
    classpath = sourceSets.integrationTest.runtimeClasspath
    outputs.upToDateWhen { false }
}

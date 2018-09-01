# depchecker-core
[![GitHub version](
https://badge.fury.io/gh/icarohs7%2Fdepchecker-core.svg)](
https://github.com/icarohs7/depchecker-core/releases)
[![Maven Central](
https://maven-badges.herokuapp.com/maven-central/com.github.icarohs7/depchecker-core/badge.svg)](
https://mvnrepository.com/artifact/com.github.icarohs7/depchecker-core)
[![GitHub license](
https://img.shields.io/github/license/icarohs7/depchecker-core.svg)](
https://github.com/icarohs7/depchecker-core/blob/master/LICENSE)

## Description
A library to check artifact versions programmatically (currently with MavenCentral repository)

## Usage
````kotlin
val artifact = Artifact("com.github.icarohs7.unoxlib:UNoxLib")
// OR
val artifact = Artifact(group = "com.github.icarohs7.unoxlib", artifactId = "UNoxLib")

// Returns a string with the last version of the artifact
assertThat(MavenCentralRepositoryImpl.getLastVersion(artifact), `is`("0.3.1"))

// Returns a list of versions of an artifact
val expectedVersionList = listOf(
    "0.3.1",
    "0.3.0",
    "0.2.4",
    "0.2.3",
    "0.2.0",
    "0.1.1"
)
assertThat(MavenCentralRepositoryImpl.getAllVersions(artifact), `is`(expectedVersionList))
````

## Extending
It is possible to easily add more repositories by simply creating
a class implementing the interface `DcRepository` and its methods, <br/>
as shown in the class `MavenCentralRepositoryImpl`, under
the package repositories

## More Examples
More usages can be found under the test package
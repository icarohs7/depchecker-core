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

## Adding to project
````groovy
dependencies {
    implementation "com.github.icarohs7:depchecker-core:$depchecker_version"
}
repositories {
    mavenCentral()
}
````

## Usage
### Version Checking
The core functionality of this library is checking versions of artifacts, as below:
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
Both functions can also be called as instance members of the class Artifact with the names <br/>
`getLastVersionAt(DcRepository): String?` and `getAllVersionsAt(DcRepository): List<String>`
### Persisting artifacts
It also exposes a interface to be implemented to save collections of artifacts to
the storage of your preference, coming with a buit-in in-memory implementation.
```kotlin
//Get an instance of the DAO used to store your saved artifacts
//getInMemoryDatabaseInstance() - Built-in implementation using RAM as storage
val artifactDao = ArtifactDao.getInMemoryDatabaseInstance()
val artifact = Artifact("com.github.icarohs7:depchecker-core")
```
* Persisting an artifact
```kotlin
artifactDao.insert(artifact)
```
* Reading the list of persisted artifacts
```kotlin
val allArtifacts = artifactDao.queryAll() 
assertThat(allArtifacts, `is`(listOf(artifact)))
```
* Removing an artifact from the storage
```kotlin
artifactDao.remove(artifact)
```
* Clearing the storage
```kotlin
artifactDao.removeAll()
```


## Extending
It is possible to easily add more repositories by simply creating
a class implementing the interface `DcRepository` and its methods, <br/>
as shown in the class `MavenCentralRepositoryImpl`, under
the package repositories, you can also implement your storage method <br/>
through the interface `ArtifactDao`

## More Examples
More usages can be found under the [test package](
https://github.com/icarohs7/depchecker-core/tree/master/src/test/kotlin/com/github/icarohs7)
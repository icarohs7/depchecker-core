# ![](https://www.tutorialsandyou.com/images/kotlin.png) depchecker-core

[![GitHub version](https://badge.fury.io/gh/icarohs7%2Fdepchecker-core.svg)](
https://github.com/icarohs7/depchecker-core/releases)
[![](https://jitpack.io/v/icarohs7/depchecker-core.svg)](
https://jitpack.io/#icarohs7/depchecker-core)
[![Build Status](https://travis-ci.org/icarohs7/depchecker-core.svg?branch=master)](
https://travis-ci.org/icarohs7/depchecker-core)
[![GitHub license](https://img.shields.io/github/license/icarohs7/depchecker-core.svg)](
https://github.com/icarohs7/depchecker-core/blob/master/LICENSE)

[![GitHub top language](https://img.shields.io/github/languages/top/icarohs7/depchecker-core.svg)](
https://github.com/icarohs7/depchecker-core/search?l=kotlin)
[![GitHub last commit](https://img.shields.io/github/last-commit/icarohs7/depchecker-core.svg)](
https://github.com/icarohs7/depchecker-core/commits/master)
[![GitHub commit activity](https://img.shields.io/github/commit-activity/w/icarohs7/depchecker-core.svg)](
https://github.com/icarohs7/depchecker-core/commits/master)
[![GitHub issues](https://img.shields.io/github/issues/icarohs7/depchecker-core.svg)](
https://github.com/icarohs7/depchecker-core/issues)

[![GitHub forks](https://img.shields.io/github/forks/icarohs7/depchecker-core.svg?style=social&label=Fork)](
https://github.com/icarohs7/depchecker-core/fork)
[![GitHub stars](https://img.shields.io/github/stars/icarohs7/depchecker-core.svg?style=social&label=Stars)](
https://github.com/icarohs7/depchecker-core)
[![GitHub watchers](https://img.shields.io/github/watchers/icarohs7/depchecker-core.svg?style=social&label=Watch)](
https://github.com/icarohs7/depchecker-core/subscription)

## Description
A library to check artifact versions programmatically (currently with MavenCentral repository)

## Adding to project
````groovy
dependencies {
    implementation "com.github.icarohs7:depchecker-core:$depchecker_version"
}
repositories {
    maven { url "https://jitpack.io" }
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
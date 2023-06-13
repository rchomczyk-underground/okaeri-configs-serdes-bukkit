# okaeri-configs-serdes-bukkit
An additional pack of serializers / transformers for [okaeri-configs](https://github.com/OkaeriPoland/okaeri-configs/) 
library to support most of the functionality offered by [Bukkit](https://dev.bukkit.org/).

#### Build and publish artifact
The most important step is to build final artifact and publish it into
maven's cache, by use of `mvn clean install` command.

#### Include dependency in your project

### Gradle
```kotlin
repositories {
    mavenLocal()
}

dependencies {
    implementation("moe.rafal:okaeri-configs-serdes-bukkit:1.1-SNAPSHOT")
}
```
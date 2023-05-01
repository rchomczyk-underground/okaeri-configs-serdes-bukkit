# okaeri-configs-serdes-bukkit
An additional pack of serializers / transformers for [okaeri-configs](https://github.com/OkaeriPoland/okaeri-configs/) 
library to support most of the functionality offered by [Bukkit](https://dev.bukkit.org/).

### Usage

```kotlin
SerdesBukkit()
```

### Gradle
```kotlin
repositories {
    maven { url = uri("https://repo.rafal.moe/snapshots/") }
}

dependencies {
    implementation("moe.rafal:okaeri-configs-serdes-bukkit:1.0-SNAPSHOT")
}
```
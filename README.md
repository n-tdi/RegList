# RegList
Register all your Listeners with a simple function.

<br>

## Using RegList

To get RegList, you can get it from [JitPack](https://jitpack.io/#professional-tdi/RegList) or follow the below guide.

### Gradle

```groovy
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
	
    dependencies {
        implementation 'com.github.professional-tdi:RegList:Tag'
    }
```


### Maven
```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

    <dependcies>
        <dependency>
            <groupId>com.github.professional-tdi</groupId>
            <artifactId>RegList</artifactId>
            <version>Tag</version>
        </dependency>
    </dependencies>
```

Replace `Tag` with the current release version below

[![](https://jitpack.io/v/professional-tdi/RegList.svg)](https://jitpack.io/#professional-tdi/RegList)

### Tutorial
Using Reglist is Super Easy.

Simply call the `registerListeners()` function in your `onEnable()` method!

```Java
import ntdi.world.reglisteners.RegListeners;

public class Example extends JavaPlugin {
    @Override
    public void onEnable() {
        registerListeners(this);
    }
}
```

That's it! Enjoy :)

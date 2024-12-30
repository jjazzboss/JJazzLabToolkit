# JJazzLab Toolkit

All [JJazzLab](https://github.com/jjazzboss/JJazzLab) core in one jar, with all dependencies included.

The JJazzLab Toolkit is the "engine" that powers the JJazzLab application: object models and algorithms, without graphical user interface.

For example, you can use it to :

- add automatic backing track generation to your app
- experiment a plugin which analyzes chord progressions
- make a small utility to batch-convert .sng files to .mp3 
- ...

![JJazzLab Toolkit architecture](https://github.com/jjazzboss/JJazzLab/blob/master/graphics/JJazzLab-Core-blocks.png)


## How to use the toolkit

For sample code how to use the main features, check out the [DemoApp](https://github.com/jjazzboss/JJazzLabToolkit/tree/main/DemoApp) sub-project.

You'll need to run your program with Java 17 and some specific JVM command line arguments (e.g. --add-opens), see `DemoApp/pom.xml` for reference.

The JJazzLab Toolkit follows the same versioning than the JJazzLab application: a new version of the toolkit is released when a new version of JJazzLab is released.

### With Maven
```
<dependency>
    <groupId>org.jjazzlab</groupId>
    <artifactId>jjazzlab-toolkit</artifactId>
    <version>4.1.0</version>
</dependency>
```

### With Gradle
```
compile 'org.jjazzlab:jjazzlab-toolkit:4.1.0'
```

### Using JJazzLab plugins with the Toolkit

The JJazzLab [jar plugins](https://github.com/jjazzboss/JJazzLabJarPlugins) can be used with this Toolkit. See the [DemoApp](https://github.com/jjazzboss/JJazzLabToolkit/tree/main/DemoApp) for sample code.

## Build

There is usually no need to build the Toolkit yourself since it is available on [Sonatype Maven Central](https://central.sonatype.com). But if you insist:
 
- Clone and build [JJazzLab](https://github.com/jjazzboss/JJazzLab) locally (`mvn clean install`), so that all JJazzLab core modules are available in your local Maven repository (usually `$HOME/.m2`)
- Clone this repo 
- Run `mvn clean install`

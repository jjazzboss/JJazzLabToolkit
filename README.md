# JJazzLab Toolkit

All [JJazzLab](https://github.com/jjazzboss/JJazzLab) core in one jar, with all dependencies included.

The JJazzLab Toolkit is the "engine" that powers the JJazzLab application: object models and algorithms, without graphical user interface.

For example, you can use it to :

- add automatic backing track generation to your app
- try developing a new backing track generation plugin
- make a small utility to batch-convert .sng files to .mp3 
- ...

![JJazzLab Toolkit architecture](https://github.com/jjazzboss/JJazzLab/blob/master/graphics/JJazzLab-Core-blocks.png)


## How to use the toolkit

The JJazzLab Toolkit requires **java 23 or later**.

For sample code how to use the main JJazzLab features, check out the [DemoApp](https://github.com/jjazzboss/JJazzLabToolkit/tree/main/DemoApp) sub-project.

The JJazzLab Toolkit follows the same versioning than the JJazzLab application: a new version of the toolkit is released when a new version of JJazzLab is released.


### With Maven
```
<dependency>
    <groupId>org.jjazzlab</groupId>
    <artifactId>jjazzlab-toolkit</artifactId>
    <version>5.0.2</version>
</dependency>
```

### With Gradle
```
compile 'org.jjazzlab:jjazzlab-toolkit:5.0.2'
```

## Build

There is usually no need to build the Toolkit yourself since it is available on [Sonatype Maven Central](https://central.sonatype.com). But if you insist:
 
- Clone and build [JJazzLab](https://github.com/jjazzboss/JJazzLab) locally (`mvn clean install`), so that all JJazzLab core modules are available in your local Maven repository (usually `$HOME/.m2`)
- Clone this repo 
- Run `mvn clean install`

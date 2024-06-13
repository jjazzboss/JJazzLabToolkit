# JJazzLab Toolkit

All [JJazzLab](https://github.com/jjazzboss/JJazzLab) core in one jar, with all dependencies included.

The JJazzLab Toolkit is the "engine" that powers the JJazzLab application: object models and algorithms, without graphical user interface.

For example, you can use it to :

- add automatic backing track generation to your app
- experiment a plugin to reharmonize songs
- make a small utility which converts in batch mode JJazzLab songs to .mp3 files
- ...

![JJazzLab Toolkit architecture](https://github.com/jjazzboss/JJazzLab/blob/master/graphics/JJazzLab-Core-blocks.png)

&nbsp;

You can combine this toolkit with JJazzLab [plugins](https://github.com/jjazzboss/JJazzLab/tree/master/plugins) such as :

- [YamJJazz](https://github.com/jjazzboss/JJazzLab/tree/master/plugins/YamJJazz): backing track generator from Yamaha style files
- [FluidSynthEmbeddedSynth](https://github.com/jjazzboss/JJazzLab/tree/master/plugins/YamJJazz): make JJazzLab use [FluidSynth](https://www.fluidsynth.org/) as its output synth


## How to use the toolkit

For sample code how to use the main features, check out the **DemoApp** sub-project.

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

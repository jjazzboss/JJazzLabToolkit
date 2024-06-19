# DemoApp

This small Maven project demonstrates how to use the JJazzLab Toolkit.

`pom.xml` sets dependencies to the JJazzLab Toolkit and 2 JJazzLab [jar plugins](https://github.com/jjazzboss/JJazzLabJarPlugins): 
- YamJJazz (Yamaha style-based rhythm engine)
- FluidSynthEmbeddedSynth (use FluidSynth as output synth)

`ToolkitDemoApp.java` uses a subset of the toolkit API: 
- setup Midi configuration (use FluidSynth if available) 
- play test notes
- initialize or load a song
- modify the song
- play the song
- export to Midi
- export to audio (if FluidSynth is available)

### Compile
mvn compile

### Run
mvn exec:java






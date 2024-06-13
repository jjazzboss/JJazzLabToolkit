# DemoApp

This small Maven project demonstrates how to use the JJazzLab Toolkit.

pom.xml defines a dependency to the JJazzLab Toolkit and to 2 JJazzLab plugins (YamJJazz rhythm engine, FluidSynthEmbeddedSynth).

ToolkitDemoApp.java uses a few of the toolkit API: 
- setup Midi configuration (use FluidSynth if available) 
- play test notes
- initialize or load a song
- modify it
- play the song
- export to Midi
- export to audio (if FluidSynth is available)

### Compile
mvn compile

### Run
mvn exec:java






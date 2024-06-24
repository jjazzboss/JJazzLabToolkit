# DemoApp

This small Maven project demonstrates how to use the JJazzLab Toolkit.

`pom.xml` sets dependencies to the JJazzLab Toolkit and to the following JJazzLab [jar plugins](https://github.com/jjazzboss/JJazzLabJarPlugins): 
- YamJJazz (Yamaha style-based rhythm engine)
- FluidSynthEmbeddedSynth (use FluidSynth as output synth)

`ToolkitDemoApp.java` will: 
- setup FluidSynth as output synth if `JJazzLab-SoundFont.sf2` is found, otherwise setup the default synth (low quality)
- play test notes
- initialize or load a song (see `PATH_TO_SNG_FILE` constant)
- modify the song
- play the song
- export to Midi
- export to audio (if FluidSynth is the output synth)

### Compile
`mvn compile`

> [!IMPORTANT]
> Java 17 is required because FluidSynthEmbeddedSynth uses Java 17 incubating features.

### Run
- For FluidSynth to work you need to download and copy [JJazzLab-SoundFont.sf2](https://musical-artifacts.com/artifacts/1036) to the project root directory (next to `pom.xml`).
- `mvn exec:exec`


Sample program output
=====================
```
--- exec:3.1.0:exec (default-cli) @ jjazzlab-toolkit-demo ---
WARNING: Using incubator modules: jdk.incubator.foreign
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Starting JJazzLabToolkit demo app... 
INFOS ToolkitDemoApp  Using PATH_TO_JJAZZLAB_SOUNDFONT_SF2=JJazzLab-SoundFont.sf2 
INFOS ToolkitDemoApp  Using PATH_TO_SNG_FILE=null 
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Setting builtin software synth... 
INFOS FluidSynthNativeLoader  extractWinLibsFromJar() Copied 0 native library files to C:\Users\Jerome\AppData\Local\Temp\fluidsynthjava 
INFOS FluidSynthNativeLoader  loadNativeLibraries() Success 
AVERTISSEMENT org.openide.util.NbPreferences  NetBeans implementation of Preferences not found 
INFOS FluidSynthJava  open() FluidSynth version=2.3.0 
INFOS FluidSynthJava  open() Native FluidSynth instance initialized 
INFOS FluidSynthJava  loadSoundFont() SoundFont successfully loaded D:\JeromeDocs\JJazzLab\src\JJazzLabToolkit\DemoApp\JJazzLab-SoundFont.sf2 
INFOS JJazzLabSequencer  JJazzLabSequencer() Setting up JJazzLab sequencer 
INFOS JJazzMidiSystem  JJazzMidiSystem() Midi out devices=[{name=Microsoft MIDI Mapper,maxReceivers=-1,maxTransmitters=0}, {name=VirtualMIDISynth #1,maxReceivers=-1,maxTransmitters=0}, {name=Microsoft GS Wavetable Synth,maxReceivers=-1,maxTransmitters=0}, {name=loopMIDI Port,maxReceivers=-1,maxTransmitters=0}] 
INFOS JJazzMidiSystem  setDefaultOutDevice() oldDevice=null newDevice=FluidSynth_MD 
INFOS FluidSynthEmbeddedSynthProvider  setEmbeddedSynthActive() b=true 
INFOS ToolkitDemoApp  Using JJazzLab FluidSynth 
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Playing test notes... 
INFOS MusicController  MusicController() Started 
INFOS ToolkitDemoApp  Did you hear the notes? 
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Populating the RhythmDatabase... 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\16beat.S556.yjz and 16beat.S556.prs 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\Cantabile.yjz and Cantabile.prs 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\JazzRock_Cz2k.S563.yjz and JazzRock_Cz2k.S563.sty 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\LingusFunk.S066.yjz and LingusFunk.S066.sty 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\SambaCity213.s460.yjz and SambaCity213.s460.sty 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\16beat.S556.yjz and 16beat.S556.prs 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\SambaCity213.s460.yjz and SambaCity213.s460.sty 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\LingusFunk.S066.yjz and LingusFunk.S066.sty 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\Cantabile.yjz and Cantabile.prs 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\JazzRock_Cz2k.S563.yjz and JazzRock_Cz2k.S563.sty 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\16BeatBallad2.S014.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\AfroCuban.S730.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\FastJazz.S741.sst 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\JazzWaltzMed.S351.sst 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\JazzWaltzSlow.S423.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\MediumJazz.S737.sst 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\PopBossa1.S629.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\PopShuffle1.S552.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Urban Funk.S066.STY 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\JazzWaltzMed.S351.sst 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Urban Funk.S066.STY 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\16BeatBallad2.S014.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\AfroCuban.S730.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\JazzWaltzSlow.S423.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\PopBossa1.S629.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\FastJazz.S741.sst 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\PopShuffle1.S552.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\MediumJazz.S737.sst 
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Creating a new song... 
INFOS ToolkitDemoApp  Song chord leadsheet= ChordLeadSheet section0=A size=12
 A(4/4)[0:0] : F7[0:0] Bb7[4:0] F7[6:0] C7[8:0] Bb7[9:0] F7[10:0]  
INFOS ToolkitDemoApp  Song structure      = size=12 spts=[  [A, r=16BeatBallad2.S014.prs, startBarIndex=0, nbBars=12]] 
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Changing the song structure... 
INFOS ToolkitDemoApp  Song structure changed= size=24 spts=[  [A, r=16BeatBallad2.S014.prs, startBarIndex=0, nbBars=12]
  [A, r=16BeatBallad2.S014.prs, startBarIndex=12, nbBars=12]] 
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Configuring Midi mix... 
INFOS DefaultMidiMixManager  DefaultMidiMixManager() Started 
INFOS ToolkitDemoApp  MidiMix[song=Simple blues in F, channels=[8, 9, 10, 11, 12, 13, 14, 15]]:
 8: [ins=Std Kit 1, settings=(v=79 t=0 r=60 c=8 p=64)]
 9: [ins=Std Kit 1, settings=(v=88 t=0 r=44 c=8 p=64)]
 10: [ins=Fingered Bass, settings=(v=71 t=0 r=5 c=34 p=64)]
 11: [ins=E.Piano 1, settings=(v=71 t=0 r=43 c=65 p=54)]
 12: [ins=Clean Guitar, settings=(v=69 t=0 r=42 c=50 p=86)]
 13: [ins=Strings, settings=(v=45 t=0 r=60 c=8 p=64)]
 14: [ins=Muted Guitar, settings=(v=44 t=0 r=52 c=20 p=36)]
 
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Playing the song... 
Press Enter to continue...

INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Exporting song to Midi + Audio files... 
INFOS SongMidiExporter  songToMidiFile() writing sequence to Midi file: C:\Users\Jerome\AppData\Local\Temp\tmp10913465283249643554.mid 
INFOS ToolkitStatusDisplayer  Using ToolkitStatusDisplayer() 
INFOS ToolkitStatusDisplayer  Set status text: S�quence Midi �crite vers C:\Users\Jerome\AppData\Local\Temp\tmp10913465283249643554.mid 
INFOS ToolkitDemoApp  Midi export to C:\Users\Jerome\AppData\Local\Temp\tmp10913465283249643554.mid complete ! 
INFOS FluidSynthJava  loadSoundFont() SoundFont successfully loaded D:\JeromeDocs\JJazzLab\src\JJazzLabToolkit\DemoApp\JJazzLab-SoundFont.sf2 
INFOS FluidSynthJava  close() Native FluidSynth instance closed 
INFOS ToolkitDemoApp  Audio (WAV) export to C:\Users\Jerome\AppData\Local\Temp\tmp10913465283249643554.wav complete ! 
INFOS ToolkitDemoApp  Converting WAV file to MP3... 
INFOS ToolkitDemoApp  Audio (MP3) export to C:\Users\Jerome\AppData\Local\Temp\tmp10913465283249643554.mp3 complete ! 
INFOS ToolkitDemoApp  Exiting 
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time:  17.093 s
Finished at: 2024-06-20T10:39:57+02:00
------------------------------------------------------------------------
```




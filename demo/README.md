# DemoApp

This small project demonstrates how to use the JJazzLab Toolkit.

What `ToolkitDemoApp.java` does: 
- try to set up FluidSynth as output synth, or fall back to the default java synth (LOW QUALITY!) 
- play test notes
- try to load the song file defined by variable `PATH_TO_SNG_FILE`, or create a new song
- modify the song
- generate the backing track for the song
- play the song
- export the song to Midi
- export the song to audio (only if FluidSynth is the output synth)

### Fluidsynth setup
For FluidSynth to work you need to:
- download and copy [JJazzLab-SoundFont.sf2](https://musical-artifacts.com/artifacts/1036) into this project root directory (next to this README)
- for Linux or MacOS: FluidSynth (>=2.2.0) must have been previously installed on your system

### Compile
`mvn clean compile`

### Run
`mvn exec:exec`

Sample program output
=====================
```
$ cd demo
$ mvn exec:exec                                                                                                                                                                                                             
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------< org.jjazzlab.toolkit:jjazzlab-toolkit-demo >-------------                                                                                                                                             
[INFO] Building JJazzLab Toolkit Demo 5.1                                                                                                                                                                                   
[INFO]   from pom.xml                                                                                                                                                                                                       
[INFO] --------------------------------[ jar ]---------------------------------                                                                                                                                             
[INFO] 
[INFO] --- exec:3.3.0:exec (default-cli) @ jjazzlab-toolkit-demo ---                                                                                                                                                        
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Starting JJazzLabToolkit demo app... 
INFOS ToolkitDemoApp  Using PATH_TO_JJAZZLAB_SOUNDFONT_SF2=JJazzLab-SoundFont.sf2
INFOS ToolkitDemoApp  Using PATH_TO_SNG_FILE=Simple.sng
AVERTISSEMENT org.openide.util.NbPreferences  NetBeans implementation of Preferences not found 
INFOS ToolkitDemoApp  Default rhythms dir.=C:\Users\Jerome\JJazzLabRhythms\Default 
INFOS ToolkitDemoApp  User rhythms dir.   =C:\Users\Jerome\JJazzLabRhythms
INFOS ToolkitDemoApp  -------------------------------------------------------
INFOS ToolkitDemoApp  Setting up JJazzLab FluidSynth...
INFOS FluidSynthNativeLoader  extractWinLibsFromJar() Copied 0 native library files to C:\cygwin64\tmp\fluidsynthjava 
INFOS FluidSynthNativeLoader  loadNativeLibraries() Success 
INFOS FluidSynthJava  open() FluidSynth version=2.3.0 
INFOS FluidSynthJava  open() Native FluidSynth instance initialized 
INFOS FluidSynthJava  loadSoundFont() SoundFont successfully loaded D:\JeromeDocs\JJazzLab\src\JJazzLabToolkit\demo\JJazzLab-SoundFont.sf2 
INFOS JJazzLabSequencer  JJazzLabSequencer() Setting up JJazzLab sequencer 
INFOS JJazzMidiSystem  JJazzMidiSystem() Midi out devices=[{name=Microsoft MIDI Mapper,maxReceivers=-1,maxTransmitters=0}, {name=VirtualMIDISynth #1,maxReceivers=-1,maxTransmitters=0}, {name=Microsoft GS Wavetable Synth,maxReceivers=-1,maxTransmitters=0}]
INFOS JJazzMidiSystem  setDefaultOutDevice() oldDevice=null newDevice=FluidSynth_MD 
INFOS FluidSynthEmbeddedSynthProvider  setEmbeddedSynthActive() b=true 
INFOS ToolkitDemoApp  FluidSynth: success
INFOS ToolkitDemoApp  -------------------------------------------------------
INFOS ToolkitDemoApp  Playing test notes...
INFOS MusicController  MusicController() Started 
INFOS ToolkitDemoApp  Did you hear the notes? 
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Populating the RhythmDatabase...
=================== RHYTHMS CREATED =============================
INFOS DefaultRhythmDatabase  addRhythmsFromRhythmProviders() rp=DUMMY_RHYTHMS 
INFOS DefaultRhythmDatabase  addRhythmsFromRhythmProviders() rp=Rythmes factices
INFOS DefaultRhythmDatabase  addRhythmsFromRhythmProviders() rp=YamJJazz extended styles
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\16beat.S556.yjz and 16beat.S556.prs
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\Cantabile.yjz and Cantabile.prs 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\JazzRock_Cz2k.S563.yjz and JazzRock_Cz2k.S563.sty 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\LingusFunk.S066.yjz and LingusFunk.S066.sty 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\YamJJazz\SambaCity213.s460.yjz and SambaCity213.s460.sty 
INFOS DefaultRhythmDatabase  addRhythmsFromRhythmProviders() rp=YamJJazz standard styles 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\16BeatBallad2.S014.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\6-8ModernBallad.S560.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\8Beat.T160.STY 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\90'sGuitarPop.S080.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\90'sOrgRockBld.T162.STY
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\AcousticRock.S080.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\AfroCuban.S730.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\BluesRock.S524.sst 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\BossaNova2.S469.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\BritPopSwing.S089.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Calypso.S354.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\ChaCha.S628.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\ClassicHipHop.S145.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\ClubBeat.S130.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Cool8Beat.S737.sst 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\CoolJazzBallad.S738.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\DiscoHouse.S145.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\EasyAcJazz.T157.STY 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\EtherealSynth.T125.STY
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\FastFolkWaltz7.S093.STY 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\FastJazz.S741.sst
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Folkball.S702.sty 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\FranklySoul.S230.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\FunkPopRock.S043.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\FunkyHouse.S322.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\GuitarPop.S557.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\HappyReggae.S655.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Jazzvocal.s264.sty
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\JazzWaltzFast.S499.sty 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\JazzWaltzMed.S351.sst
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\JazzWaltzSlow.S423.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\KoolFunk.STY 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Mambo5.S722.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\MediumJazz.S737.sst
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\ModChartPop.S209.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\ModPickin!.T151.STY 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\MrMac'sBlues.T148.STY
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\PopBossa1.S629.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\PopRumba.S625.bcs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\PopShuffle1.S552.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\RockShuffle.S547.bcs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Soul.S199.prs
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\SoulBeat.STY
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\SoulR&B.S130.prs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\StandardRock.STY
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Swing1.S737.bcs 
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Urban Funk.S066.STY
INFOS YamahaRhythmProvider  readFast() Reading C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\USChartHit.S001.prs 
INFOS DefaultRhythmDatabase  addRhythmsFromRhythmProviders() rp=jjSwing styles 
INFOS YamJJazzRhythmProvider  readFast() Reading C:\cygwin64\tmp\psBase3592777847492091111.yjz and psBase3592777847492091111.sst 
INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Loading song file Simple.sng... 
INFOS Song  loadFromFile() Loading song file D:\JeromeDocs\JJazzLab\src\JJazzLabToolkit\demo\Simple.sng
INFOS ToolkitDemoApp  Song chord leadsheet= ChordLeadSheet section0=A size=6 
 A(4/4)[0:0] : CM7[0:0] Gm7[2:0]
 END(4/4)[4:0] : AbM7[4:0]
INFOS ToolkitDemoApp  Song structure      = size=22 spts=[A[0;3]-Cool8Beat.S737.sst, A[4;7]-Cool8Beat.S737.sst, A[8;11]-Cool8Beat.S737.sst, A[12;15]-Cool8Beat.S737.sst, A[16;19]-Cool8Beat.S737.sst, END[20;21]-Cool8Beat.S737.sst]
INFOS ToolkitDemoApp  -------------------------------------------------------
INFOS ToolkitDemoApp  Changing the song structure...
INFOS ToolkitDemoApp  Song structure changed= size=26 spts=[A[0;3]-Cool8Beat.S737.sst, A[4;7]-Cool8Beat.S737.sst, A[8;11]-Cool8Beat.S737.sst, A[12;15]-Cool8Beat.S737.sst, A[16;19]-Cool8Beat.S737.sst, A[20;23]-Cool8Beat.S737.sst, END[24;25]-Cool8Beat.S737.sst]
INFOS ToolkitDemoApp  -------------------------------------------------------
INFOS ToolkitDemoApp  Configuring Midi mix...
INFOS DefaultMidiMixManager  DefaultMidiMixManager() Started
(Fichier mix du rythme C:\Users\Jerome\JJazzLabRhythms\Default\Yamaha\Cool8Beat.S737.mix chargÚ)
INFOS ToolkitDemoApp  MidiMix[song=Simple, channels=[8, 9, 10, 11, 12, 13, 14]]:
 8: [ins=Jazz Kit, settings=(v=78 t=0 r=28 c=8 p=64)]
 9: [ins=Jazz Kit, settings=(v=102 t=0 r=29 c=8 p=64)]
 10: [ins=Fingered Bass, settings=(v=80 t=0 r=0 c=8 p=64)]
 11: [ins=Clean Guitar, settings=(v=46 t=0 r=30 c=36 p=36)]
 12: [ins=E.Piano 1, settings=(v=75 t=0 r=46 c=19 p=87)]
 13: [ins=OB Strings, settings=(v=40 t=0 r=50 c=8 p=64)]
 14: [ins=Nylon Guitar, settings=(v=60 t=0 r=51 c=8 p=93)]

INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Playing the song...
Press Enter to continue...

INFOS ToolkitDemoApp  ------------------------------------------------------- 
INFOS ToolkitDemoApp  Exporting song to Midi + Audio files...
INFOS SongMidiExporter  songToMidiFile() writing sequence to Midi file: C:\cygwin64\tmp\tmp9661678891567996229.mid 
(SÚquence Midi Úcrite vers C:\cygwin64\tmp\tmp9661678891567996229.mid)
INFOS ToolkitDemoApp  Midi export to C:\cygwin64\tmp\tmp9661678891567996229.mid complete !
INFOS FluidSynthJava  loadSoundFont() SoundFont successfully loaded D:\JeromeDocs\JJazzLab\src\JJazzLabToolkit\demo\JJazzLab-SoundFont.sf2 
INFOS FluidSynthJava  close() Native FluidSynth instance closed 
INFOS ToolkitDemoApp  Audio (WAV) export to C:\cygwin64\tmp\tmp9661678891567996229.wav complete ! 
INFOS ToolkitDemoApp  Converting WAV file to MP3...
INFOS ToolkitDemoApp  Audio (MP3) export to C:\cygwin64\tmp\tmp9661678891567996229.mp3 complete ! 
INFOS ToolkitDemoApp  Exiting 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS                                                                                                                                                                                                        
[INFO] ------------------------------------------------------------------------                                                                                                                                             
[INFO] Total time:  12.718 s                                                                                                                                                                                                
[INFO] Finished at: 2026-01-09T19:17:03+01:00                                                                                                                                                                               
[INFO] ------------------------------------------------------------------------       
```




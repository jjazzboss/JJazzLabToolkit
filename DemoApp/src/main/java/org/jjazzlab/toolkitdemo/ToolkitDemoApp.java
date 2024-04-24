package org.jjazzlab.toolkitdemo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiUnavailableException;
import org.jjazz.chordleadsheet.api.ChordLeadSheet;
import org.jjazz.chordleadsheet.api.UnsupportedEditException;
import org.jjazz.chordleadsheet.api.item.CLI_Factory;
import org.jjazz.embeddedsynth.api.EmbeddedSynthException;
import org.jjazz.embeddedsynth.spi.EmbeddedSynthProvider;
import org.jjazz.fluidsynthembeddedsynth.api.FluidSynthEmbeddedSynth;
import org.jjazz.fluidsynthembeddedsynth.api.FluidSynthEmbeddedSynthProvider;
import org.jjazz.harmony.api.Position;
import org.jjazz.harmony.api.TimeSignature;
import org.jjazz.importers.api.TextReader;
import org.jjazz.midi.api.JJazzMidiSystem;
import org.jjazz.midimix.api.MidiMix;
import org.jjazz.midimix.spi.MidiMixManager;
import org.jjazz.musiccontrol.api.MusicController;
import org.jjazz.musiccontrol.api.SongMidiExporter;
import org.jjazz.musiccontrol.api.playbacksession.StaticSongSession;
import org.jjazz.outputsynth.api.OutputSynth;
import org.jjazz.outputsynth.spi.OutputSynthManager;
import org.jjazz.rhythm.api.MusicGenerationException;
import org.jjazz.rhythm.api.rhythmparameters.RP_STD_Variation;
import org.jjazz.rhythmdatabase.api.DefaultRhythmDatabase;
import org.jjazz.rhythmdatabase.api.RhythmDatabase;
import org.jjazz.song.api.Song;
import org.jjazz.song.api.SongCreationException;
import org.jjazz.song.api.SongFactory;
import org.jjazz.songcontext.api.SongContext;
import org.jjazz.songstructure.api.SongStructure;
import org.jjazz.testplayerservice.spi.TestPlayer;
import org.openide.util.Exceptions;

/**
 * This demonstrates usage of the JJazzLab toolkit.
 */
public class ToolkitDemoApp
{

    static Logger getLogger()
    {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");
        return Logger.getLogger(ToolkitDemoApp.class.getSimpleName());
    }
    private static final Logger LOGGER = getLogger();

    // Change these 2 values to match your system
    private static String PATH_TO_JJAZZLAB_SOUNDFONT_SF2 = "JJazzLab-SoundFont.sf2";
    private static String PATH_TO_SNG_FILE = "/home/jerome/MySong.sng";


    public static void main(String[] args)
    {
        LOGGER.info("-------------------------------------------------------");
        LOGGER.info("Starting JJazzLabToolkit demo app...");
        if (!new File(PATH_TO_JJAZZLAB_SOUNDFONT_SF2).exists())
        {
            PATH_TO_JJAZZLAB_SOUNDFONT_SF2 = null;
        }
        if (!new File(PATH_TO_SNG_FILE).exists())
        {
            PATH_TO_SNG_FILE = null;
        }
        LOGGER.log(Level.INFO, "Using PATH_TO_JJAZZLAB_SOUNDFONT_SF2={0}", PATH_TO_JJAZZLAB_SOUNDFONT_SF2);
        LOGGER.log(Level.INFO, "Using PATH_TO_SNG_FILE={0}", PATH_TO_SNG_FILE);


        // =============================================================================================
        LOGGER.info("-------------------------------------------------------");
        LOGGER.info("Setting Midi OUT device...");

        boolean fluidsynthInstalled = PATH_TO_JJAZZLAB_SOUNDFONT_SF2 != null && activateFluidSynth(PATH_TO_JJAZZLAB_SOUNDFONT_SF2);
        if (!fluidsynthInstalled)
        {
            LOGGER.info("Using builtin Java synth as sound device (LOW QUALITY!)");
            var jms = JJazzMidiSystem.getInstance();
            try
            {
                jms.setDefaultOutDevice(jms.getJavaInternalSynth());
            } catch (MidiUnavailableException ex)
            {
                exitWithError(ex);
            }
        }

        // =============================================================================================
        LOGGER.info("-------------------------------------------------------");
        LOGGER.log(Level.INFO, "Playing test notes...");
        try
        {
            TestPlayer.getDefault().playTestNotes();        // blocking
            LOGGER.log(Level.INFO, "Did you hear the notes?");
        } catch (MusicGenerationException ex)
        {
            exitWithError(ex);
        }


        // =============================================================================================
        LOGGER.info("-------------------------------------------------------");
        LOGGER.log(Level.INFO, "Populating the RhythmDatabase...");
        DefaultRhythmDatabase rdb = (DefaultRhythmDatabase) RhythmDatabase.getDefault();
        // This will poll all the RhythmProvider instances available in the global lookup 
        // To get the Yamaha file based "YamJJazz" RhythmProvider, make sure pom.xml includes a dependency on the org.jjazzlab.plugins-yamjjazz plugin.
        rdb.addRhythmsFromRhythmProviders(false, false, true);


        // =============================================================================================
        Song song = null;
        ChordLeadSheet chordLeadsheet = null;
        SongStructure songStructure = null;
        if (PATH_TO_SNG_FILE != null)
        {
            LOGGER.info("-------------------------------------------------------");
            LOGGER.log(Level.INFO, "Loading song file {0}...", PATH_TO_SNG_FILE);
            try
            {
                song = Song.loadFromFile(new File(PATH_TO_SNG_FILE));
                chordLeadsheet = song.getChordLeadSheet();
                songStructure = song.getSongStructure();
            } catch (SongCreationException ex)
            {
                exitWithError(ex);
            }
        }
        if (song == null)
        {
            LOGGER.info("-------------------------------------------------------");
            LOGGER.info("Creating a new song...");
            // 12 bars with just an initial "A" Section in 4/4 and its corresponding SongPart
            song = SongFactory.getInstance().createEmptySong("Simple blues in F", 12, "A", TimeSignature.FOUR_FOUR, null);
            chordLeadsheet = song.getChordLeadSheet();
            songStructure = song.getSongStructure();
            CLI_Factory clif = CLI_Factory.getDefault();        // ChordLeadSheetItem factory
            try
            {
                chordLeadsheet.addItem(clif.createChordSymbol("F7", 0, 0));            // 0-based bar, beat  
                chordLeadsheet.addItem(clif.createChordSymbol("Bb7", 4, 0));
                chordLeadsheet.addItem(clif.createChordSymbol("F7", 6, 0));
                chordLeadsheet.addItem(clif.createChordSymbol("C7", 8, 0));
                chordLeadsheet.addItem(clif.createChordSymbol("Bb7", 9, 0));
                chordLeadsheet.addItem(clif.createChordSymbol("F7", 10, 0));
            } catch (ParseException ex)
            {
                exitWithError(ex);
            }

            // Or simpler using the TextReader song importer:
//            TextReader tr = new TextReader(
//                    """
//                    |4/4 F7|    |     |    |
//                    |Bb7   |    |F7   |    | 
//                    |C7    |Bb7 |F7   |    |""");
//            song = tr.readSong();           // might be null
//            cls = song.getChordLeadSheet();
//            ss = song.getSongStructure();

            // Or simpler using the TextReader song importer:
//            TextReader tr = new TextReader(
//                    """
//                    |4/4 F7|    |     |    |
//                    |Bb7   |    |F7   |    | 
//                    |C7    |Bb7 |F7   |    |""");
//            song = tr.readSong();           // might be null
//            cls = song.getChordLeadSheet();
//            ss = song.getSongStructure();
        }

        LOGGER.log(Level.INFO, "Song chord leadsheet= {0}", chordLeadsheet.toDebugString());
        LOGGER.log(Level.INFO, "Song structure      = {0}", songStructure);


        // =============================================================================================
        LOGGER.info("-------------------------------------------------------");
        LOGGER.info("Changing the song structure...");
        // Duplicate the initial SongPart
        var spt0 = songStructure.getSongPart(0);
        int spt0Size = spt0.getNbBars();        // =12
        var spt1 = spt0.clone(null, spt0Size, spt0Size, spt0.getParentSection());  // Create a SongPart copy which starts right after spt0
        try
        {
            songStructure.addSongParts(Arrays.asList(spt1));   // Song now contains 2 identical song parts, song size is 24 bars
        } catch (UnsupportedEditException ex)
        {
            exitWithError(ex);
        }
        // Change the Variation rhythm parameter value of the 2nd song part
        var spt1Rhythm = spt1.getRhythm();
        var rpVariation = RP_STD_Variation.getVariationRp(spt1Rhythm);
        if (rpVariation != null)
        {
            songStructure.setRhythmParameterValue(spt1, rpVariation, "Main D-1");
        }
        LOGGER.log(Level.INFO, "Song structure changed= {0}", songStructure);


        
        // =============================================================================================
        LOGGER.info("-------------------------------------------------------");
        LOGGER.info("Configuring Midi...");
        MidiMix midiMix = null;
        try
        {
            midiMix = MidiMixManager.getDefault().createMix(song); // throws MidiUnavailableException
        } catch (MidiUnavailableException ex)
        {
            exitWithError(ex);
        }
        OutputSynth outputSynth = OutputSynthManager.getDefault().getDefaultOutputSynth();  // OutputSynth for the current default Midi OUT device
        // Try to fix the MidiMix to match the OutputSynth capabilities
        // Important when using a GM synth (drums on channel 10 only), especially with Yamaha styles which often use 2 drums/percussion channels (9 and 10).        
        outputSynth.fixInstruments(midiMix, true);
        // Send all Midi messages to configure the connected synth
        outputSynth.getUserSettings().sendModeOnUponPlaySysexMessages();    // Configure GM, GM2, XG, GS if required
        midiMix.sendAllMidiMixMessages();
        midiMix.sendAllMidiVolumeMessages();
        LOGGER.log(Level.INFO, midiMix.toDumpString());


        // =============================================================================================
        LOGGER.info("-------------------------------------------------------");
        LOGGER.info("Playing the song for 60 seconds...");
        try
        {
            song.setTempo(120);
            var mc = MusicController.getInstance();
            var session = StaticSongSession.getSession(new SongContext(song, midiMix));
            mc.setPlaybackSession(session, false);             // throws MusicGenerationException
            mc.play(0);     // throws MusicGenerationException
            Thread.sleep(60000);
        } catch (MusicGenerationException | InterruptedException ex)
        {
            exitWithError(ex);
        }

//
//        try
//        {
//            LOGGER.log(Level.INFO, "Generating music and exporting to Midi file...");
//            File midiFile = Files.createTempFile("tmp", ".mid").toFile();
//            MidiMix mm = MidiMixManager.getDefault().createMix(song);
////            for (var insMix : mm.getInstrumentMixes())
////            {
////                insMix.setMute(true);
////            }
//            if (SongMidiExporter.songToMidiFile(song, mm, midiFile, null))
//            {
//                File audioFile = Files.createTempFile("tmp", ".wav").toFile();
//                fluidSynth.generateWavFile(midiFile, audioFile);
//                LOGGER.log(Level.INFO, "Export to {0} complete !", midiFile.getAbsolutePath());
//            }
//
//
//            song.setTempo(120);
//            var mc = MusicController.getInstance();
//            var session = StaticSongSession.getSession(new SongContext(song, mm));
//            mc.setPlaybackSession(session, false);
//            mc.play(0);
//            Thread.sleep(5000);
//            mc.pause();
//            Thread.sleep(2000);
//            mc.resume();
//            Thread.sleep(3000);
//
//
//        } catch (ParseException | UnsupportedEditException | IOException | MidiUnavailableException | EmbeddedSynthException | MusicGenerationException | InterruptedException ex)
//        {
//            Exceptions.printStackTrace(ex);
//        }

        LOGGER.info("Exiting");
        System.exit(0);
    }

    private static void exitWithError(Exception ex)
    {
        Exceptions.printStackTrace(ex);
        System.exit(1);
    }

    /**
     * Set up the FluidSynthEmbeddedSynth instance.
     * <p>
     * pom.xml must include a dependency on the org.jjazzlab.plugins-fluidsynthembeddedsynth plugin.
     *
     * @param soundfontPath
     * @return True if fluidsynth was successfully setup
     */
    private static boolean activateFluidSynth(String soundfontPath)
    {
        boolean b = false;
        File soundfontFile = new File(soundfontPath);


        var synthProvider = EmbeddedSynthProvider.getDefaultProvider();
        if (synthProvider instanceof FluidSynthEmbeddedSynthProvider fluidSynthProvider)
        {
            var fluidSynth = fluidSynthProvider.getEmbeddedSynth();
            fluidSynth.setSoundFontFile(soundfontFile);
            try
            {
                synthProvider.setEmbeddedSynthActive(true);
                b = true;
            } catch (EmbeddedSynthException ex)
            {
                LOGGER.log(Level.WARNING, "setupFluidSynth() Can't activate FluidSynth ex={0}", ex.getMessage());
            }

        } else
        {
            LOGGER.log(Level.SEVERE, "setupFluidSynth() No FluidSynthEmbeddedSynthProvider instance found");
        }

        return b;
    }


}

package org.jjazzlab.toolkitdemo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiUnavailableException;
import org.jjazz.chordleadsheet.api.ChordLeadSheet;
import org.jjazz.chordleadsheet.api.UnsupportedEditException;
import org.jjazz.chordleadsheet.api.item.CLI_Factory;
import org.jjazz.embeddedsynth.api.EmbeddedSynth;
import org.jjazz.embeddedsynth.api.EmbeddedSynthException;
import org.jjazz.embeddedsynth.api.Mp3Encoder;
import org.jjazz.embeddedsynth.spi.EmbeddedSynthProvider;
import org.jjazz.embeddedsynth.spi.Mp3EncoderProvider;
import org.jjazz.harmony.api.TimeSignature;
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
import org.jjazz.utilities.api.Utilities;
import org.openide.util.Exceptions;

/**
 * This demonstrates usage of the JJazzLab toolkit.
 */
public class ToolkitDemoApp
{

    // ============================================================================================
    // These are relative paths from the Maven project directory (the one containing pom.xml)
    private static String PATH_TO_JJAZZLAB_SOUNDFONT_SF2 = "JJazzLab-SoundFont.sf2";  // Required for FluidSynthEmbeddedSynth to work
    private static String PATH_TO_SNG_FILE = "";            // Try to open this .sng file if not empty, otherwise program will create a song from scratch
    // =============================================================================================

    
    static
    {
        // Set string format for all Loggers
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s %3$s  %5$s %n");
    }
    private static final Logger LOGGER = Logger.getLogger(ToolkitDemoApp.class.getSimpleName());

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
        LOGGER.info("Setting builtin software synth...");

        EmbeddedSynth fluidSynth = activateFluidSynth(PATH_TO_JJAZZLAB_SOUNDFONT_SF2);
        if (fluidSynth != null)
        {
            LOGGER.info("Using JJazzLab FluidSynth");
        } else
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
        // NOTE: the org.jjazzlab.plugins:yamjjazz-jar plugin should be in the classpath in order to get the Yamaha style-based "YamJJazz" RhythmProviders
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

            // Or simpler, use the TextReader song importer:
//            TextReader tr = new TextReader(
//                    """
//                    |4/4 F7|    |     |    |
//                    |Bb7   |    |F7   |    | 
//                    |C7    |Bb7 |F7   |    |""");
//            song = tr.readSong();           
//            chordLeadsheet = song.getChordLeadSheet();
//            songStructure = song.getSongStructure();
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
            String currentValue = spt1.getRPValue(rpVariation);
            String nextValue = rpVariation.getNextValue(currentValue);
            songStructure.setRhythmParameterValue(spt1, rpVariation, nextValue);
        } else
        {
            LOGGER.log(Level.WARNING, "Rhythm {0} does not use a RP_STD_VARIATION rhythm parameter", spt1Rhythm.getName());
        }
        LOGGER.log(Level.INFO, "Song structure changed= {0}", songStructure);


        // =============================================================================================
        LOGGER.info("-------------------------------------------------------");
        LOGGER.info("Configuring Midi mix...");
        MidiMix midiMix = null;
        try
        {
            midiMix = MidiMixManager.getDefault().createMix(song); // throws MidiUnavailableException
        } catch (MidiUnavailableException ex)
        {
            exitWithError(ex);
        }
        OutputSynth outputSynth = OutputSynthManager.getDefault().getDefaultOutputSynth();  // OutputSynth for the current default Midi OUT device
        // Try to make the MidiMix match the OutputSynth capabilities
        // This is important when using a GM synth (drums on channel 10 only), rerouting to Drums channel might be required, especially with Yamaha styles 
        // which often use 2 drums/percussion channels (9 and 10).        
        outputSynth.fixInstruments(midiMix, true);
        // Send all Midi messages to configure the connected synth (for each used channel select patch and set volume/pan/chorus/reverb settings)
        outputSynth.getUserSettings().sendModeOnUponPlaySysexMessages();    // Configure GM, GM2, XG, GS if required
        midiMix.sendAllMidiMixMessages();
        midiMix.sendAllMidiVolumeMessages();
        LOGGER.log(Level.INFO, midiMix.toDumpString());


        // =============================================================================================
        LOGGER.info("-------------------------------------------------------");
        LOGGER.info("Playing the song...");
        try
        {
            song.setTempo(120);
            var mc = MusicController.getInstance();
            var session = StaticSongSession.getSession(new SongContext(song, midiMix));
            mc.setPlaybackSession(session, false);             // throws MusicGenerationException
            mc.play(0);     // throws MusicGenerationException            
        } catch (MusicGenerationException ex)
        {
            exitWithError(ex);
        }

        Scanner s = new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        s.nextLine();


        // =============================================================================================
        LOGGER.info("-------------------------------------------------------");
        LOGGER.info("Exporting song to Midi + Audio files...");
        try
        {
            File midiFile = Files.createTempFile("tmp", ".mid").toFile();       // throws IOException

            if (SongMidiExporter.songToMidiFile(song, midiMix, midiFile, null))
            {
                LOGGER.log(Level.INFO, "Midi export to {0} complete !", midiFile.getAbsolutePath());

                if (fluidSynth != null)
                {
                    // WAV export
                    File wavFile = new File(Utilities.replaceExtension(midiFile.getAbsolutePath(), ".wav"));
                    fluidSynth.generateWavFile(midiFile, wavFile);        // throws EmbeddedSynthException
                    LOGGER.log(Level.INFO, "Audio (WAV) export to {0} complete !", wavFile.getAbsolutePath());


                    // MP3 conversion
                    Mp3Encoder encoder = Mp3EncoderProvider.getDefault();
                    if (encoder != null)
                    {
                        LOGGER.log(Level.INFO, "Converting WAV file to MP3...");
                        File mp3File = new File(Utilities.replaceExtension(midiFile.getAbsolutePath(), ".mp3"));
                        encoder.encode(wavFile, mp3File, false, false);
                        LOGGER.log(Level.INFO, "Audio (MP3) export to {0} complete !", mp3File.getAbsolutePath());
                    }
                } else
                {
                    LOGGER.info("JJazzLab embedded FluidSynth is not installed, can not export to audio.");
                }
            }
        } catch (IOException | EmbeddedSynthException ex)
        {
            exitWithError(ex);
        }


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
     * NOTE: the org.jjazzlab.plugins:fluidsynthembeddedsynth-jar plugin must be in the classpath.
     *
     * @param soundfontPath
     * @return The fluidsynth instance if success, null otherwise
     */
    private static EmbeddedSynth activateFluidSynth(String soundfontPath)
    {
        if (soundfontPath == null)
        {
            return null;
        }
        EmbeddedSynth synth = null;
        File soundfontFile = new File(soundfontPath);

        var synthProvider = EmbeddedSynthProvider.getDefaultProvider();
        if (synthProvider != null && synthProvider.getId().equals("FluidSynthEmbeddedSynthProviderId"))
        {
            synth = synthProvider.getEmbeddedSynth();
            synth.configure(soundfontFile);
            try
            {
                // This will load the FluidSynth native resources and do everything required so that FluidSynth becomes the output synth
                synthProvider.setEmbeddedSynthActive(true);
            } catch (EmbeddedSynthException ex)
            {
                LOGGER.log(Level.WARNING, "activateFluidSynth() Can't activate FluidSynth ex={0}", ex.getMessage());
                synth = null;
            }

        } else
        {
            LOGGER.log(Level.SEVERE, "activateFluidSynth() No FluidSynthEmbeddedSynthProvider instance found");
        }

        return synth;
    }


}

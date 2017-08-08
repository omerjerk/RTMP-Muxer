package in.omerjerk.rtmp.muxer;

import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by umair on 8/7/17.
 */
public class TwitchTest {

    private static final String FRAMES_FILE = "frames.h264";
    private static final String META_DATA_FILE = "meta.txt";

    //Test for the consistency of test data
    @Before
    public void begin() throws IOException {
        byte[] frames = Util.getResourceAsBytes(FRAMES_FILE);
        ArrayList<JsonObject> metas = Util.getMetaDataArray(Util.getResourceAsString(META_DATA_FILE));

        int metaSize = 0;
        for (JsonObject meta: metas) {
            metaSize += meta.get("s").getAsInt();
        }

        assertEquals(metaSize, frames.length);
        System.out.println("meta length = " + metaSize);
        System.out.println("frames length = " + frames.length);
    }

    @Test
    public void basicTest() {
        assertEquals(3, 1 + 2);
    }
}

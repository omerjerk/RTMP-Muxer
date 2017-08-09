package in.omerjerk.rtmp.muxer;

import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by umair on 8/7/17.
 */
public class TwitchTest {

    private static final String FRAMES_FILE = "frames.h264";
    private static final String META_DATA_FILE = "meta.txt";

    //Test for the consistency of h264 test data
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
    public void twitchStreamTest() throws InterruptedException {
        //used for synchronising the test
        final CountDownLatch lock = new CountDownLatch(1);

        final RtmpMuxer muxer = new RtmpMuxer("live-syd.twitch.tv", 1935, new Time() {
            @Override
            public long getCurrentTimestamp() {
                return System.currentTimeMillis();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                muxer.start(new RtmpConnectionListener() {
                    @Override
                    public void onConnected() {
                        Util.d("onConnected()");
                        try {
                            muxer.createStream("live_42658716_xYX27AvDAlboF059hbPPhSIkwGcjcU");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onReadyToPublish() {
                        Util.d("onReadyToPublish()");
                        lock.countDown();
                    }

                    @Override
                    public void onConnectionError(IOException e) {
                        e.printStackTrace();
                        Util.d("onConnectionError()");
                    }
                }, "app", null, null);
            }
        }).start();

        lock.await(15000, TimeUnit.MILLISECONDS);
    }
}

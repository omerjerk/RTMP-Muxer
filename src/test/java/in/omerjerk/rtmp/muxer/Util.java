package in.omerjerk.rtmp.muxer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by umair on 8/8/17.
 */
public class Util {

    public static byte[] getResourceAsBytes(String fileName) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(fileName).getPath());
        return Files.readAllBytes(path);
    }

    public static String getResourceAsString(String fileName) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);

        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(is, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }

    public static ArrayList<JsonObject> getMetaDataArray(String metaDatas) {
        ArrayList<JsonObject> result = new ArrayList<>();

        String[] lines = metaDatas.split("\n");
        for (String metaData: lines) {
            result.add(new JsonParser().parse(metaData).getAsJsonObject());
        }

        return result;
    }
}

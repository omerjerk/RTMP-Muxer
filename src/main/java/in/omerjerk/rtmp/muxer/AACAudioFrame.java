package in.omerjerk.rtmp.muxer;

/**
 *
 * @author Benoit LETONDOR
 */
public interface AACAudioFrame
{
    /**
     * Return the timestamp of the frame in milliseconds, relative to the beginning of the stream
     * (first frame = 0)
     *
     * @return the timestamp in milliseconds.
     */
    long getTimestamp();

    /**
     * Return the AAC audio data.
     *
     * @return data of the stream
     */
    byte[] getData();
}

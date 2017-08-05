package in.omerjerk.rtmp.muxer;

/**
 * Interface that defines an AAC header, extracted from {android.media.MediaRecorder}.
 *
 * @author Benoit LETONDOR
 */
public interface AACAudioHeader
{
    /**
     * Return the AAC header data of the AAC stream.
     *
     * @return bytes of data
     */
    byte[] getData();

    /**
     * Return the number of channels of the AAC stream.
     *
     * @return number of channels
     */
    int getNumberOfChannels();

    /**
     * Return the sample size index of the AAC stream.
     *
     * @return sample size index
     */
    int getSampleSizeIndex();
}

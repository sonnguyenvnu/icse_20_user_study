/** 
 * Writes multiple sync samples from a byte array into a track. <p> This method does not inspect the contents of the samples. The contents has to match the format and dimensions of the media in this track.
 * @param track The track index.
 * @param sampleCount The number of samples.
 * @param data The encoded sample data.
 * @param off The start offset in the data.
 * @param len The number of bytes to write. Must be dividable by sampleCount.
 * @param sampleDuration The duration of a sample. All samples musthave the same duration.
 * @throws IllegalArgumentException if the duration is less than 1.
 * @throws IOException if writing the image failed.
 */
public void writeSamples(int track,int sampleCount,byte[] data,int off,int len,int sampleDuration) throws IOException {
  writeSamples(track,sampleCount,data,off,len,sampleDuration,true);
}

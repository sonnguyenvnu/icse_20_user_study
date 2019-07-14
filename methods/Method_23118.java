/** 
 * Adds an audio track to the movie.
 * @param compressionType The QuickTime 4-character code.A list of supported 4-Character codes is given in qtff, table 3-7, page 113.
 * @param timeScale The media time scale between 1 and 2^32.
 * @param sampleRate The sample rate. The integer portion must match the{@code timeScale}.
 * @param numberOfChannels The number of channels: 1 for mono, 2 for stereo.
 * @param sampleSizeInBits The number of bits in a sample: 8 or 16.
 * @param isCompressed Whether the sound is compressed.
 * @param frameDuration The frame duration, expressed in the media’stimescale, where the timescale is equal to the sample rate. For uncompressed formats, this field is always 1.
 * @param frameSize  For uncompressed audio, the number of bytes in asample for a single channel (sampleSize divided by 8). For compressed audio, the number of bytes in a frame.
 * @throws IllegalArgumentException if the audioFormat is not 4 characters long,if the time scale is not between 1 and 2^32, if the integer portion of the sampleRate is not equal to the timeScale, if numberOfChannels is not 1 or 2.
 */
public void addAudioTrack(String compressionType,long timeScale,double sampleRate,int numberOfChannels,int sampleSizeInBits,boolean isCompressed,int frameDuration,int frameSize) throws IOException {
  ensureStarted();
  if (compressionType == null || compressionType.length() != 4) {
    throw new IllegalArgumentException("audioFormat must be 4 characters long:" + compressionType);
  }
  if (timeScale < 1 || timeScale > (2L << 32)) {
    throw new IllegalArgumentException("timeScale must be between 1 and 2^32:" + timeScale);
  }
  if (timeScale != (int)Math.floor(sampleRate)) {
    throw new IllegalArgumentException("timeScale: " + timeScale + " must match integer portion of sampleRate: " + sampleRate);
  }
  if (numberOfChannels != 1 && numberOfChannels != 2) {
    throw new IllegalArgumentException("numberOfChannels must be 1 or 2: " + numberOfChannels);
  }
  if (sampleSizeInBits != 8 && sampleSizeInBits != 16) {
    throw new IllegalArgumentException("sampleSize must be 8 or 16: " + numberOfChannels);
  }
  AudioTrack t=new AudioTrack();
  t.mediaCompressionType=compressionType;
  t.mediaTimeScale=timeScale;
  t.soundSampleRate=sampleRate;
  t.soundCompressionId=isCompressed ? -2 : -1;
  t.soundNumberOfChannels=numberOfChannels;
  t.soundSampleSize=sampleSizeInBits;
  t.soundSamplesPerPacket=frameDuration;
  if (isCompressed) {
    t.soundBytesPerPacket=frameSize;
    t.soundBytesPerFrame=frameSize * numberOfChannels;
  }
 else {
    t.soundBytesPerPacket=frameSize / numberOfChannels;
    t.soundBytesPerFrame=frameSize;
  }
  t.soundBytesPerSample=sampleSizeInBits / 8;
  tracks.add(t);
}

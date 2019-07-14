/** 
 * Returns the bitrate of this WAV. 
 */
public int getBitrate(){
  return sampleRateHz * bitsPerSample * numChannels;
}

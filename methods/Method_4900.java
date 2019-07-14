/** 
 * Returns whether the decoder is known to set the number of audio channels in the output format to 2 for the given input format, whilst only actually outputting a single channel. <p> If true is returned then we explicitly override the number of channels in the output format, setting it to 1.
 * @param name The decoder name.
 * @param format The input format.
 * @return True if the decoder is known to set the number of audio channels in the output formatto 2 for the given input format, whilst only actually outputting a single channel. False otherwise.
 */
private static boolean codecNeedsMonoChannelCountWorkaround(String name,Format format){
  return Util.SDK_INT <= 18 && format.channelCount == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(name);
}

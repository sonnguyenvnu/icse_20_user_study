/** 
 * Returns whether the decoder is an H.264/AVC decoder known to fail if NAL units are queued before the codec specific data. <p> If true is returned, the renderer will work around the issue by discarding data up to the SPS.
 * @param name The name of the decoder.
 * @param format The format used to configure the decoder.
 * @return True if the decoder is known to fail if NAL units are queued before CSD.
 */
private static boolean codecNeedsDiscardToSpsWorkaround(String name,Format format){
  return Util.SDK_INT < 21 && format.initializationData.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(name);
}

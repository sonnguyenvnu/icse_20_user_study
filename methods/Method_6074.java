/** 
 * Returns a maximum input buffer size for a given codec and format.
 * @param codecInfo Information about the {@link MediaCodec} being configured.
 * @param format The format.
 * @return A maximum input buffer size in bytes, or {@link Format#NO_VALUE} if a maximum could notbe determined.
 */
private static int getMaxInputSize(MediaCodecInfo codecInfo,Format format){
  if (format.maxInputSize != Format.NO_VALUE) {
    int totalInitializationDataSize=0;
    int initializationDataCount=format.initializationData.size();
    for (int i=0; i < initializationDataCount; i++) {
      totalInitializationDataSize+=format.initializationData.get(i).length;
    }
    return format.maxInputSize + totalInitializationDataSize;
  }
 else {
    return getCodecMaxInputSize(codecInfo,format.sampleMimeType,format.width,format.height);
  }
}

/** 
 * Returns  {@link MediaCodecInfo}s for the given codec  {@code key} in the order given by{@code mediaCodecList}.
 * @param key The codec key.
 * @param mediaCodecList The codec list.
 * @param requestedMimeType The originally requested MIME type, which may differ from the codeckey MIME type if the codec key is being considered as a fallback.
 * @return The codec information for usable codecs matching the specified key.
 * @throws DecoderQueryException If there was an error querying the available decoders.
 */
private static ArrayList<MediaCodecInfo> getDecoderInfosInternal(CodecKey key,MediaCodecListCompat mediaCodecList,String requestedMimeType) throws DecoderQueryException {
  try {
    ArrayList<MediaCodecInfo> decoderInfos=new ArrayList<>();
    String mimeType=key.mimeType;
    int numberOfCodecs=mediaCodecList.getCodecCount();
    boolean secureDecodersExplicit=mediaCodecList.secureDecodersExplicit();
    for (int i=0; i < numberOfCodecs; i++) {
      android.media.MediaCodecInfo codecInfo=mediaCodecList.getCodecInfoAt(i);
      String codecName=codecInfo.getName();
      if (isCodecUsableDecoder(codecInfo,codecName,secureDecodersExplicit,requestedMimeType)) {
        for (        String supportedType : codecInfo.getSupportedTypes()) {
          if (supportedType.equalsIgnoreCase(mimeType)) {
            try {
              CodecCapabilities capabilities=codecInfo.getCapabilitiesForType(supportedType);
              boolean secure=mediaCodecList.isSecurePlaybackSupported(mimeType,capabilities);
              boolean forceDisableAdaptive=codecNeedsDisableAdaptationWorkaround(codecName);
              if ((secureDecodersExplicit && key.secure == secure) || (!secureDecodersExplicit && !key.secure)) {
                decoderInfos.add(MediaCodecInfo.newInstance(codecName,mimeType,capabilities,forceDisableAdaptive,false));
              }
 else               if (!secureDecodersExplicit && secure) {
                decoderInfos.add(MediaCodecInfo.newInstance(codecName + ".secure",mimeType,capabilities,forceDisableAdaptive,true));
                return decoderInfos;
              }
            }
 catch (            Exception e) {
              if (Util.SDK_INT <= 23 && !decoderInfos.isEmpty()) {
                Log.e(TAG,"Skipping codec " + codecName + " (failed to query capabilities)");
              }
 else {
                Log.e(TAG,"Failed to query codec " + codecName + " (" + supportedType + ")");
                throw e;
              }
            }
          }
        }
      }
    }
    return decoderInfos;
  }
 catch (  Exception e) {
    throw new DecoderQueryException(e);
  }
}

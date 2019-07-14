@SuppressLint("NewApi") public static MediaCodecInfo selectCodec(String mimeType){
  int numCodecs=MediaCodecList.getCodecCount();
  MediaCodecInfo lastCodecInfo=null;
  for (int i=0; i < numCodecs; i++) {
    MediaCodecInfo codecInfo=MediaCodecList.getCodecInfoAt(i);
    if (!codecInfo.isEncoder()) {
      continue;
    }
    String[] types=codecInfo.getSupportedTypes();
    for (    String type : types) {
      if (type.equalsIgnoreCase(mimeType)) {
        lastCodecInfo=codecInfo;
        String name=lastCodecInfo.getName();
        if (name != null) {
          if (!name.equals("OMX.SEC.avc.enc")) {
            return lastCodecInfo;
          }
 else           if (name.equals("OMX.SEC.AVC.Encoder")) {
            return lastCodecInfo;
          }
        }
      }
    }
  }
  return lastCodecInfo;
}

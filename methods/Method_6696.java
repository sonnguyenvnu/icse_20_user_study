@SuppressLint("NewApi") public static int selectColorFormat(MediaCodecInfo codecInfo,String mimeType){
  MediaCodecInfo.CodecCapabilities capabilities=codecInfo.getCapabilitiesForType(mimeType);
  int lastColorFormat=0;
  for (int i=0; i < capabilities.colorFormats.length; i++) {
    int colorFormat=capabilities.colorFormats[i];
    if (isRecognizedFormat(colorFormat)) {
      lastColorFormat=colorFormat;
      if (!(codecInfo.getName().equals("OMX.SEC.AVC.Encoder") && colorFormat == 19)) {
        return colorFormat;
      }
    }
  }
  return lastColorFormat;
}

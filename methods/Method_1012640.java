public String match(String container,String videoCodec,String audioCodec,int nbAudioChannels,int frequency,int bitrate,int framerate,int videoWidth,int videoHeight,Map<String,String> extras){
  String matchedMimeType=null;
  for (  SupportSpec supportSpec : supportSpecs) {
    if (supportSpec.match(container,videoCodec,audioCodec,nbAudioChannels,frequency,bitrate,framerate,videoWidth,videoHeight,extras)) {
      matchedMimeType=supportSpec.mimeType;
      break;
    }
  }
  return matchedMimeType;
}

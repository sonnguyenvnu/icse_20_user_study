@Override public void init(ExtractorOutput output){
  extractorOutput=output;
  trackOutput=extractorOutput.track(0,C.TRACK_TYPE_AUDIO);
  extractorOutput.endTracks();
  try {
    decoderJni=new FlacDecoderJni();
  }
 catch (  FlacDecoderException e) {
    throw new RuntimeException(e);
  }
}

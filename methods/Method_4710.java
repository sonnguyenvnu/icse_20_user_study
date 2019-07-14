public static boolean verifyBitstreamType(ParsableByteArray data){
  try {
    return VorbisUtil.verifyVorbisHeaderCapturePattern(0x01,data,true);
  }
 catch (  ParserException e) {
    return false;
  }
}

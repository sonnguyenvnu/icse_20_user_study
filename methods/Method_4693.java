public static boolean verifyBitstreamType(ParsableByteArray data){
  if (data.bytesLeft() < OPUS_SIGNATURE.length) {
    return false;
  }
  byte[] header=new byte[OPUS_SIGNATURE.length];
  data.readBytes(header,0,OPUS_SIGNATURE.length);
  return Arrays.equals(header,OPUS_SIGNATURE);
}

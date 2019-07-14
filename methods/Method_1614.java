private static void putEOI(byte[] imageBytes,int offset){
  imageBytes[offset]=(byte)JfifUtil.MARKER_FIRST_BYTE;
  imageBytes[offset + 1]=(byte)JfifUtil.MARKER_EOI;
}

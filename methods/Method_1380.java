public static boolean isExtendedWebpHeaderWithAlpha(final byte[] imageHeaderBytes,final int offset){
  boolean isVp8x=matchBytePattern(imageHeaderBytes,offset + 12,WEBP_VP8X_BYTES);
  boolean hasAlphaBit=(imageHeaderBytes[offset + 20] & 16) == 16;
  return isVp8x && hasAlphaBit;
}

public static boolean isAnimatedWebpHeader(final byte[] imageHeaderBytes,final int offset){
  boolean isVp8x=matchBytePattern(imageHeaderBytes,offset + 12,WEBP_VP8X_BYTES);
  boolean hasAnimationBit=(imageHeaderBytes[offset + 20] & 2) == 2;
  return isVp8x && hasAnimationBit;
}

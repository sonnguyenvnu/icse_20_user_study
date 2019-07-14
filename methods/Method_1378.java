public static boolean isWebpSupportedByPlatform(final byte[] imageHeaderBytes,final int offset,final int headerSize){
  if (isSimpleWebpHeader(imageHeaderBytes,offset)) {
    return sIsSimpleWebpSupported;
  }
  if (isLosslessWebpHeader(imageHeaderBytes,offset)) {
    return sIsExtendedWebpSupported;
  }
  if (isExtendedWebpHeader(imageHeaderBytes,offset,headerSize)) {
    if (isAnimatedWebpHeader(imageHeaderBytes,offset)) {
      return false;
    }
    return sIsExtendedWebpSupported;
  }
  return false;
}

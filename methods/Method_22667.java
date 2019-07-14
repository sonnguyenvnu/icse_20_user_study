static public String getVariant(int platform,String arch,int bits){
  if (platform == PConstants.LINUX && bits == 32 && "arm".equals(Platform.getNativeArch())) {
    return "armv6hf";
  }
 else   if (platform == PConstants.LINUX && bits == 64 && "aarch64".equals(Platform.getNativeArch())) {
    return "arm64";
  }
  return Integer.toString(bits);
}

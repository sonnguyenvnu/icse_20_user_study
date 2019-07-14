public static String stringFromBytes(byte[] bytes,Charset charset){
  if (bytes == null) {
    return null;
  }
  return new String(bytes,charset);
}

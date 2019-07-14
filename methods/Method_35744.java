public static byte[] bytesFromString(String str,Charset charset){
  if (str == null) {
    return null;
  }
  return str.getBytes(charset);
}

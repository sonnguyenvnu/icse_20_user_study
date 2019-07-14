private static String getHeader(byte[] header){
  StringBuilder str=new StringBuilder();
  for (int i=0; i < header.length; i++) {
    str.append((char)header[i]);
  }
  return str.toString();
}

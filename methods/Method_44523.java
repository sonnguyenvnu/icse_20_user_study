private static String arrayHex(byte[] a){
  StringBuilder sb=new StringBuilder();
  for (  byte b : a) {
    sb.append(String.format("%02x",b & 0xff));
  }
  return sb.toString();
}

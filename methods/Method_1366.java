public static String convertToHex(byte[] raw) throws UnsupportedEncodingException {
  StringBuilder sb=new StringBuilder(raw.length);
  for (  byte b : raw) {
    int v=b & 0xFF;
    sb.append((char)HEX_CHAR_TABLE[v >>> 4]);
    sb.append((char)HEX_CHAR_TABLE[v & 0xF]);
  }
  return sb.toString();
}

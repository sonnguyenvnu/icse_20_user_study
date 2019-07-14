public static String join(String[] track){
  StringBuilder buf=new StringBuilder(11 * track.length);
  for (  String str : track) {
    if (0 != buf.length()) {
      buf.append(",");
    }
    buf.append(str);
  }
  return buf.toString();
}

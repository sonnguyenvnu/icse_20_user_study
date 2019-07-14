public static String join(long[] follows){
  StringBuilder buf=new StringBuilder(11 * follows.length);
  for (  long follow : follows) {
    if (0 != buf.length()) {
      buf.append(",");
    }
    buf.append(follow);
  }
  return buf.toString();
}

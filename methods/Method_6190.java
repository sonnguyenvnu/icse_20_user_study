public String readString(int len,String enc) throws IOException {
  String s=new String(readBytes(len),enc);
  int end=s.indexOf(0);
  return end < 0 ? s : s.substring(0,end);
}

private static String subString(String src,int offset,int len){
  char[] chars=new char[len];
  src.getChars(offset,offset + len,chars,0);
  return new String(chars);
}

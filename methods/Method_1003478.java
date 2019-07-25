private static String limit(String value,int length,boolean singleLine,boolean elipsize){
  StringBuilder buf=new StringBuilder(value);
  int indexNewLine=buf.indexOf(System.getProperty("line.separator"));
  int endIndex=singleLine && indexNewLine < length ? indexNewLine : length < buf.length() ? length : -1;
  if (endIndex != -1) {
    buf.setLength(endIndex);
    if (elipsize) {
      buf.append("...");
    }
  }
  return buf.toString();
}

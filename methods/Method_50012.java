private static String cutString(String src,int expectSize){
  if (src.length() == 0) {
    return "";
  }
  StringBuilder builder=new StringBuilder(expectSize);
  final int length=src.length();
  for (int i=0, size=0; i < length; i=Character.offsetByCodePoints(src,i,1)) {
    int codePoint=Character.codePointAt(src,i);
    if (Character.charCount(codePoint) == 1) {
      size+=1;
      if (size > expectSize) {
        break;
      }
      builder.append((char)codePoint);
    }
 else {
      char[] chars=Character.toChars(codePoint);
      size+=chars.length;
      if (size > expectSize) {
        break;
      }
      builder.append(chars);
    }
  }
  return builder.toString();
}

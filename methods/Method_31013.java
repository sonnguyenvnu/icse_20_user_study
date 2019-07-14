private static boolean codePointEqualsIgnoreCase(int codePoint1,int codePoint2){
  codePoint1=Character.toUpperCase(codePoint1);
  codePoint2=Character.toUpperCase(codePoint2);
  if (codePoint1 == codePoint2) {
    return true;
  }
  codePoint1=Character.toLowerCase(codePoint1);
  codePoint2=Character.toLowerCase(codePoint2);
  return codePoint1 == codePoint2;
}

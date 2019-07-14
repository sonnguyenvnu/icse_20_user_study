public int tabOffsetToJavaLine(int tabIndex,int tabOffset){
  int javaOffset=tabOffsetToJavaOffset(tabIndex,tabOffset);
  return offsetToLine(javaCode,javaOffset);
}

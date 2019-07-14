public void outLeftRightNewLine(final Chalk256 chalk256Left,final String stringLeft,final Chalk256 chalk256Right,String stringRight,final int width){
  final int leftLen=stringLeft.length();
  final int rightLen=stringRight.length();
  final int availWidth=width - 1;
  int delta=leftLen + rightLen - availWidth;
  if (delta > 0) {
    if (stringRight.length() >= delta + 3) {
      stringRight=stringRight.substring(delta + 3);
      stringRight="..." + stringRight;
    }
 else {
      stringRight="";
    }
  }
  out(chalk256Left,stringLeft);
  space();
  while (delta++ < 0) {
    space();
  }
  out(chalk256Right,stringRight);
  newLine();
}

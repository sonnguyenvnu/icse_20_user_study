public static List<FieldFormatterCleanup> parse(String formatterString){
  if ((formatterString == null) || formatterString.isEmpty()) {
    return new ArrayList<>();
  }
  List<FieldFormatterCleanup> actions=new ArrayList<>();
  int startIndex=0;
  String remainingString=formatterString;
  remainingString=StringUtil.unifyLineBreaks(remainingString,"");
  try {
    while (startIndex < formatterString.length()) {
      int currentIndex=remainingString.indexOf('[');
      String fieldKey=remainingString.substring(0,currentIndex);
      int endIndex=remainingString.indexOf(']');
      startIndex+=endIndex + 1;
      int tokenIndex=remainingString.indexOf(',');
      do {
        boolean doBreak=false;
        if ((tokenIndex == -1) || (tokenIndex > endIndex)) {
          tokenIndex=remainingString.indexOf(']');
          doBreak=true;
        }
        String formatterKey=remainingString.substring(currentIndex + 1,tokenIndex);
        actions.add(new FieldFormatterCleanup(fieldKey,getFormatterFromString(formatterKey)));
        remainingString=remainingString.substring(tokenIndex + 1);
        if (remainingString.startsWith("]") || doBreak) {
          break;
        }
        tokenIndex=remainingString.indexOf(',');
        currentIndex=-1;
      }
 while (true);
    }
  }
 catch (  StringIndexOutOfBoundsException ignore) {
    return actions;
  }
  return actions;
}

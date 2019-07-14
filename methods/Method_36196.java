private static boolean anyQuestionsMarksAreEscaped(String s){
  int index=s.indexOf('?');
  if (index == -1) {
    return true;
  }
  if (index < 2) {
    return false;
  }
  String sub=s.substring(index - 2,index);
  return sub.equals("\\\\");
}

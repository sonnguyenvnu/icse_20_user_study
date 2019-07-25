public boolean ready(){
  final String text=getDataSource().peek(0).getElement();
  if (text.equals("{")) {
    final String text1=getDataSource().peek(1).getElement();
    if (text1.equals("T")) {
      return true;
    }
    if (text1.length() == 2 && text1.startsWith("T")) {
      final char c=text1.charAt(1);
      return TableStrategy.fromChar(c) != null;
    }
    return false;
  }
  return false;
}

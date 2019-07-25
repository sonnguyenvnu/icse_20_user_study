public boolean ready(){
  final String text=getDataSource().peek(0).getElement();
  if (text.equals("{") || text.equals("{+") || text.equals("{#") || text.equals("{!") || text.equals("{-")) {
    final String text1=getDataSource().peek(1).getElement();
    if (text1.matches("[NSEW]=")) {
      return true;
    }
    return false;
  }
  return false;
}

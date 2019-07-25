public boolean ready(){
  final String text=getDataSource().peek(0).getElement();
  if (text.equals("{S") || text.equals("{S-") || text.equals("{SI")) {
    return true;
  }
  return false;
}

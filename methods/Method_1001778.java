public boolean ready(){
  final String text=getDataSource().peek(0).getElement();
  if (text.equals("{*")) {
    return true;
  }
  return false;
}

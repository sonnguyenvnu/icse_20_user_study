public boolean ready(){
  final String text=dataSource.peek(0).getElement();
  if (isLine(text,'-')) {
    return true;
  }
  if (isLine(text,'=')) {
    return true;
  }
  if (isLine(text,'~')) {
    return true;
  }
  if (isLine(text,'.')) {
    return true;
  }
  return false;
}

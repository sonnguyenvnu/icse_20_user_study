public boolean ready(){
  final String text=dataSource.peek(0).getElement();
  return text.startsWith("[") && text.endsWith("]");
}

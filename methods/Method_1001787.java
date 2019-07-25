public boolean ready(){
  final String text=dataSource.peek(0).getElement();
  if (text.startsWith("{") || text.startsWith("}")) {
    return false;
  }
  return StringUtils.trin(text).length() > 0;
}

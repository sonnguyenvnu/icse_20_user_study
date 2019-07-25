@Override public void update(Command command) throws IllegalArgumentException {
  final Set<String> states=this.states;
  String valueStr=command.toString();
  if (states != null && !states.contains(valueStr)) {
    throw new IllegalArgumentException("Value " + valueStr + " not within range");
  }
  state=new StringType(valueStr);
}

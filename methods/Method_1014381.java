@Override public void update(Command command) throws IllegalArgumentException {
  if (command instanceof DateTimeType) {
    state=((DateTimeType)command);
  }
 else {
    state=DateTimeType.valueOf(command.toString());
  }
}

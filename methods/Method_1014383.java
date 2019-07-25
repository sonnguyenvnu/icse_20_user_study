@Override public void update(Command command) throws IllegalArgumentException {
  if (command instanceof PointType) {
    state=((PointType)command);
  }
 else {
    state=PointType.valueOf(command.toString());
  }
}

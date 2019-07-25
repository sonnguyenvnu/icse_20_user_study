@Override public void update(Command command) throws IllegalArgumentException {
  if (command instanceof OpenClosedType) {
    state=(OpenClosedType)command;
  }
 else {
    final String updatedValue=command.toString();
    if (openString.equals(updatedValue)) {
      state=OpenClosedType.OPEN;
    }
 else     if (closeString.equals(updatedValue)) {
      state=OpenClosedType.CLOSED;
    }
 else {
      state=OpenClosedType.valueOf(updatedValue);
    }
  }
}

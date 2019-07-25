@Override public void update(Command command) throws IllegalArgumentException {
  if (command instanceof OnOffType) {
    state=(OnOffType)command;
  }
 else {
    final String updatedValue=command.toString();
    if (onString.equals(updatedValue)) {
      state=OnOffType.ON;
    }
 else     if (offString.equals(updatedValue)) {
      state=OnOffType.OFF;
    }
 else {
      state=OnOffType.valueOf(updatedValue);
    }
  }
}

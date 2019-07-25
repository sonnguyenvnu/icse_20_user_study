@Override public void update(Command command) throws IllegalArgumentException {
  if (command instanceof StopMoveType) {
    throw new IllegalStateException("Cannot call update() with StopMoveType");
  }
 else   if (command instanceof PercentType) {
    state=(PercentType)command;
  }
 else {
    throw new IllegalStateException("Cannot call update() with custom stop/move/up/down");
  }
}

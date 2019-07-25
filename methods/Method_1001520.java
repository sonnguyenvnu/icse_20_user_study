static void close(State s) throws IOException {
  if (s.runningState == UNINITIALIZED) {
    throw new IllegalStateException("State MUST be initialized");
  }
  if (s.runningState == CLOSED) {
    return;
  }
  s.runningState=CLOSED;
  if (s.input != null) {
    Utils.closeInput(s.input);
    s.input=null;
  }
}

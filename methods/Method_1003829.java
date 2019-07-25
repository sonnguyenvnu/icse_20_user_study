@Override public void accept(Throwable e) throws Exception {
  if (e instanceof OnErrorNotImplementedException) {
    Promise.error(e.getCause()).then(Action.noop());
  }
 else   if (e instanceof UndeliverableException) {
    Promise.error(e.getCause()).then(Action.noop());
  }
 else {
    Promise.error(e).then(Action.noop());
  }
}

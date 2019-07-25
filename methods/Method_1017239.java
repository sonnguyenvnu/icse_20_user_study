@Override public FutureReporter.Context setup(){
  pending.inc();
  return new SemanticContext(timer.time());
}

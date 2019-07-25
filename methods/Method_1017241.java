@Override public Context time(){
  final Stopwatch stopwatch=Stopwatch.createStarted();
  return new Context(){
    @Override public long stop(){
      final long elapsed=stopwatch.elapsed(TimeUnit.NANOSECONDS);
      value.set(elapsed);
      return elapsed;
    }
    @Override public void finished(){
      stop();
    }
  }
;
}

@Override public Iterable<Measurement> measure() throws Exception {
  stopwatch.start();
  benchmarkMethod.invoke(benchmark);
  long nanos=stopwatch.stop().elapsed(NANOSECONDS);
  stopwatch.reset();
  return ImmutableSet.of(new Measurement.Builder().description("runtime").weight(1).value(Value.create(nanos,"ns")).build());
}

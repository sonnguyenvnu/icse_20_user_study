@Override public Iterable<Measurement> measure() throws Exception {
  double measured=(Double)benchmarkMethod.invoke(benchmark);
  return ImmutableSet.of(new Measurement.Builder().value(Value.create(measured,unit)).weight(1).description(description).build());
}

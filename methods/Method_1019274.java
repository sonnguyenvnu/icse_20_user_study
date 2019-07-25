@Override public ImmutableList<Measurement> measure() throws Exception {
  return measureAllocations(benchmark,benchmarkMethod).toMeasurements();
}

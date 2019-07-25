@Override public Iterable<Measurement> measure() throws Exception {
  AllocationStats baseline=measureAllocations(benchmark,benchmarkMethod,0);
  int measurementReps=random.nextInt(MAX_REPS) + 1;
  AllocationStats measurement=measureAllocations(benchmark,benchmarkMethod,measurementReps);
  return measurement.minus(baseline).toMeasurements();
}

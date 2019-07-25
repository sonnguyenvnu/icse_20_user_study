@Override public Iterable<Measurement> measure() throws Exception {
  long nanos=invokeTimeMethod(nextReps);
  Measurement measurement=new Measurement.Builder().description("runtime").value(Value.create(nanos,"ns")).weight(nextReps).build();
  totalReps+=nextReps;
  totalNanos+=nanos;
  return ImmutableSet.of(measurement);
}

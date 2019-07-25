/** 
 * This benchmark attempts to measure performance of  {@link TextFormat#extract(Object,Getter)}.
 */
@Benchmark @BenchmarkMode(Mode.SampleTime) @OutputTimeUnit(TimeUnit.NANOSECONDS) public SpanContext extract(Data data) throws SpanContextParseException {
  return data.textFormatBase.extract(data.spanContextHeaders);
}

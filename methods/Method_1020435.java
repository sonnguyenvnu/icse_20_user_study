/** 
 * This benchmark attempts to measure performance of  {@link TextFormat#inject(SpanContext,Object,Setter)}.
 */
@Benchmark @BenchmarkMode(Mode.SampleTime) @OutputTimeUnit(TimeUnit.NANOSECONDS) public Map<String,String> inject(Data data){
  Map<String,String> carrier=new HashMap<String,String>();
  data.textFormatBase.inject(data.spanContext,carrier);
  return carrier;
}

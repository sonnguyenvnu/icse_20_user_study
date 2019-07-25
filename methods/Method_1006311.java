@Benchmark public int iand(BenchmarkState state){
  Container result=state.bc.iand(state.rc);
  return result.getCardinality();
}

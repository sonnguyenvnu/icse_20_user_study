private static <C>double benchmark(BenchmarkMethods<C> methods){
  long msStart=System.currentTimeMillis();
  for (int runNumber=0; runNumber < TOTAL_RUNS; ++runNumber) {
    final C xs=methods.range(0,100);
    C r=xs;
    for (int i=1; i < 2000; ++i) {
      r=methods.append(r,xs);
    }
    List<Integer> r2=methods.unbox(r);
    for (    Integer x : r2) {
    }
  }
  long msEnd=System.currentTimeMillis();
  return (msEnd - msStart) / ((double)TOTAL_RUNS);
}

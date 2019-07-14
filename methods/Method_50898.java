private long analyzeCode(CPD cpd){
  long start=System.currentTimeMillis();
  cpd.go();
  long stop=System.currentTimeMillis();
  return stop - start;
}

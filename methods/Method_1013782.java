@Override public ProxyContentParser.SubOptionParser parse(String... subOption){
  if (subOption.length < 2) {
    throw new IllegalArgumentException(String.format("Compress Option not correct %s",Arrays.deepToString(subOption)));
  }
  algorithm=new CompressAlgorithm(){
    @Override public String version(){
      return subOption[1];
    }
    @Override public AlgorithmType getType(){
      return AlgorithmType.valueOf(subOption[0]);
    }
  }
;
  return this;
}

public boolean equals(long bucket,HyperLogLogPlusPlus other){
  return Objects.equals(p,other.p) && Objects.equals(algorithm.get(bucket),other.algorithm.get(bucket)) && Objects.equals(getComparableData(bucket),other.getComparableData(bucket));
}

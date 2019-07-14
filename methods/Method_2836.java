public double computeMutualInformation(String first,String second){
  return Math.log(Math.max(Predefine.MIN_PROBABILITY,getPairFrequency(first,second) / (totalPair / 2)) / Math.max(Predefine.MIN_PROBABILITY,(getTermFrequency(first) / totalTerm * getTermFrequency(second) / totalTerm)));
}

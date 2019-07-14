public double computeMutualInformation(PairFrequency pair){
  return Math.log(Math.max(Predefine.MIN_PROBABILITY,pair.getValue() / totalPair) / Math.max(Predefine.MIN_PROBABILITY,(CoreDictionary.getTermFrequency(pair.first) / (double)CoreDictionary.totalFrequency * CoreDictionary.getTermFrequency(pair.second) / (double)CoreDictionary.totalFrequency)));
}

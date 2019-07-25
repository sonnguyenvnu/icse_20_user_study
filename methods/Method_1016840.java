public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  FeatureSequence ret=new FeatureSequenceWithBigrams(getDataAlphabet(),biDictionary,ts);
  carrier.setData(ret);
  return carrier;
}

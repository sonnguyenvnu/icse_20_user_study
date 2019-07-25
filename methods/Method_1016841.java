public Instance pipe(Instance carrier){
  carrier.setData(new FeatureVectorSequence((Alphabet)getDataAlphabet(),(TokenSequence)carrier.getData(),binary,augmentable,growAlphabet));
  return carrier;
}

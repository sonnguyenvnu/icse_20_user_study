public Instance pipe(Instance carrier){
  if (augmentable)   carrier.setData(new AugmentableFeatureVector((Alphabet)getDataAlphabet(),((Token)carrier.getData()).getFeatures(),binary));
 else   carrier.setData(new FeatureVector((Alphabet)getDataAlphabet(),((Token)carrier.getData()).getFeatures(),binary));
  return carrier;
}

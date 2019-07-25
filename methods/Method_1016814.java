@Override public Instance pipe(Instance carrier){
  FeatureSequence fs=(FeatureSequence)carrier.getData();
  carrier.setData(new FeatureVector(fs,binary));
  return carrier;
}

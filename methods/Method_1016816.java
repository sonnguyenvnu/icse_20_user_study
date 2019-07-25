@Override public Instance pipe(Instance carrier){
  FeatureVector fv=(FeatureVector)carrier.getData();
  carrier.setData(new FeatureVector(fv,fv.getAlphabet(),null,null));
  return carrier;
}

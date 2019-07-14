public boolean open(FeatureIndex featureIndex,int nbest,int vlevel,double costFactor){
  if (costFactor <= 0.0) {
    System.err.println("cost factor must be positive");
    return false;
  }
  nbest_=nbest;
  vlevel_=vlevel;
  feature_index_=featureIndex;
  feature_index_.setCostFactor_(costFactor);
  ysize_=feature_index_.ysize();
  return true;
}

public boolean open(FeatureIndex featureIndex){
  mode_=Mode.LEARN;
  feature_index_=featureIndex;
  ysize_=feature_index_.ysize();
  return true;
}

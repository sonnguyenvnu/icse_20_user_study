public boolean setModel(ModelImpl model){
  mode_=Mode.TEST;
  feature_index_=model.getFeatureIndex_();
  nbest_=model.getNbest_();
  vlevel_=model.getVlevel_();
  ysize_=feature_index_.ysize();
  return true;
}

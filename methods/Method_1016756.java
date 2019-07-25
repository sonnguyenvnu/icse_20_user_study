public int value(int field){
  FeatureVector fv=values(field);
  return (fv == null) ? -1 : fv.indexAtLocation(0);
}

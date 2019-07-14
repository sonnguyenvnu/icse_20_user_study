private void addTransitionFeatures(TagSet tagSet){
  for (int i=0; i < tagSet.size(); i++) {
    idOf("BL=" + tagSet.stringOf(i));
  }
  idOf("BL=_BL_");
}

@Override public void marginAuto(YogaEdge edge){
  mPrivateFlags|=PFLAG_MARGIN_AUTO_IS_SET;
  ArrayList<YogaEdge> mMarginAutos=(ArrayList<YogaEdge>)getOrCreateObjectProps().get(INDEX_MarginAutos);
  if (mMarginAutos == null) {
    mMarginAutos=new ArrayList<>(2);
    getOrCreateObjectProps().append(INDEX_MarginAutos,mMarginAutos);
  }
  mMarginAutos.add(edge);
}

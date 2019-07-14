@Override public void marginPercent(YogaEdge edge,float percent){
  mPrivateFlags|=PFLAG_MARGIN_PERCENT_IS_SET;
  Edges mMarginPercents=(Edges)getOrCreateObjectProps().get(INDEX_MarginPercents);
  if (mMarginPercents == null) {
    mMarginPercents=new Edges();
    getOrCreateObjectProps().append(INDEX_MarginPercents,mMarginPercents);
  }
  mMarginPercents.set(edge,percent);
}

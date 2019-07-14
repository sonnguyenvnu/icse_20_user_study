@Override public void paddingPercent(YogaEdge edge,float percent){
  mPrivateFlags|=PFLAG_PADDING_PERCENT_IS_SET;
  Edges mPaddingPercents=(Edges)getOrCreateObjectProps().get(INDEX_PaddingPercents);
  if (mPaddingPercents == null) {
    mPaddingPercents=new Edges();
    getOrCreateObjectProps().append(INDEX_PaddingPercents,mPaddingPercents);
  }
  mPaddingPercents.set(edge,percent);
}

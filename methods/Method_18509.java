@Override public void positionPercent(YogaEdge edge,float percent){
  mPrivateFlags|=PFLAG_POSITION_PERCENT_IS_SET;
  Edges mPositionPercents=(Edges)getOrCreateObjectProps().get(INDEX_PositionPercents);
  if (mPositionPercents == null) {
    mPositionPercents=new Edges();
    getOrCreateObjectProps().append(INDEX_PositionPercents,mPositionPercents);
  }
  mPositionPercents.set(edge,percent);
}

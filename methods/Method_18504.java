@Override public void marginPx(YogaEdge edge,@Px int margin){
  mPrivateFlags|=PFLAG_MARGIN_IS_SET;
  Edges mMargins=(Edges)getOrCreateObjectProps().get(INDEX_Margins);
  if (mMargins == null) {
    mMargins=new Edges();
    getOrCreateObjectProps().append(INDEX_Margins,mMargins);
  }
  mMargins.set(edge,margin);
}

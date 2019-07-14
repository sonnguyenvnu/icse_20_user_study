@Override public void minWidthPx(@Px int minWidth){
  mPrivateFlags|=PFLAG_MIN_WIDTH_IS_SET;
  getOrCreateIntProps().append(INDEX_MinWidthPx,minWidth);
}

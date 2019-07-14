@Override public void widthPx(@Px int width){
  mPrivateFlags|=PFLAG_WIDTH_IS_SET;
  getOrCreateIntProps().append(INDEX_WidthPx,width);
}

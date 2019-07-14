@Override public void flexGrow(float flexGrow){
  mPrivateFlags|=PFLAG_FLEX_GROW_IS_SET;
  getOrCreateFloatProps().append(INDEX_FlexGrow,flexGrow);
}

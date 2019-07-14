@Override public void flexBasisPercent(float percent){
  mPrivateFlags|=PFLAG_FLEX_BASIS_PERCENT_IS_SET;
  getOrCreateFloatProps().append(INDEX_FlexBasisPercent,percent);
}

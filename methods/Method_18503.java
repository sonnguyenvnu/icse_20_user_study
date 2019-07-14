@Override public void duplicateParentState(boolean duplicateParentState){
  mPrivateFlags|=PFLAG_DUPLICATE_PARENT_STATE_IS_SET;
  mDuplicateParentState=duplicateParentState;
}

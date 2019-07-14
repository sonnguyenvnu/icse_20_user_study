/** 
 * Mark this node as a nested tree root holder. 
 */
@Override public void markIsNestedTreeHolder(TreeProps currentTreeProps){
  getOrCreateNestedTreeProps().mIsNestedTreeHolder=true;
  getOrCreateNestedTreeProps().mPendingTreeProps=TreeProps.copy(currentTreeProps);
}

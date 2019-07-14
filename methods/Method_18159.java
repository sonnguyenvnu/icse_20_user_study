/** 
 * Release properties which are not longer required for the current layout pass or release properties which should be reset during reconciliation.
 */
private void clean(){
  mComponents=new ArrayList<>();
  mDiffNode=null;
  mDebugComponents=null;
  resetResolvedLayoutProperties();
}

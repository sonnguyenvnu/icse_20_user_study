/** 
 * Call this method when assigning a new  {@link LayoutOutput} to an existing MountItem. In thiscase we don't want to update mMountViewFlags since those flags are only used to determine the initial state of the view content, which we will have already done in init(). If it is done again now some of the values may be wrong (e.g. the Litho framework may add a click listener to a view that was not originally clickable.
 */
void update(LayoutOutput layoutOutput){
  mComponent=layoutOutput.getComponent();
  if (mComponent == null) {
    throw new RuntimeException("Trying to update a MountItem with a null Component!");
  }
  mLayoutFlags=layoutOutput.getFlags();
  mImportantForAccessibility=layoutOutput.getImportantForAccessibility();
  mOrientation=layoutOutput.getOrientation();
  mTransitionId=layoutOutput.getTransitionId();
  mNodeInfo=null;
  mViewNodeInfo=null;
  if (layoutOutput.getNodeInfo() != null) {
    mNodeInfo=layoutOutput.getNodeInfo();
  }
  if (layoutOutput.getViewNodeInfo() != null) {
    mViewNodeInfo=layoutOutput.getViewNodeInfo();
  }
}

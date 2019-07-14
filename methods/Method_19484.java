/** 
 * Whether first/last visible positions should be updated. If this returns true, we should not do any computations based on current first/last visible positions until they are updated.
 */
@UiThread boolean shouldUpdate(){
  return mCurrentFirstVisiblePosition < 0 || mCurrentLastVisiblePosition < 0 || mShouldUpdate;
}

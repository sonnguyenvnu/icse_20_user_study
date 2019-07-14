/** 
 * Gets the controller ready to display the images. <p>The containing view must call this method from both  {@link View#onFinishTemporaryDetach()}and  {@link View#onAttachedToWindow()}.
 */
public void onAttach(){
  if (mIsAttached) {
    return;
  }
  mIsAttached=true;
  for (int i=0; i < mHolders.size(); ++i) {
    mHolders.get(i).onAttach();
  }
}

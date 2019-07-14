/** 
 * Open a camera device and start showing camera preview. This is typically called from {@link Activity#onResume()}.
 */
public void start(){
  if (!mImpl.start()) {
    Parcelable state=onSaveInstanceState();
    mImpl=new Camera1(mCallbacks,createPreviewImpl(getContext()));
    onRestoreInstanceState(state);
    mImpl.start();
  }
}

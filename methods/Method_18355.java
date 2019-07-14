/** 
 * To be called whenever the components needs to start the mount process from scratch e.g. when the component's props or layout change or when the components gets attached to a host.
 */
void setDirty(){
  assertMainThread();
  mIsDirty=true;
  mPreviousLocalVisibleRect.setEmpty();
}

/** 
 * True if we have manually unmounted content (e.g. via unmountAllItems) which means that while we may not have a new LayoutState, the mounted content does not match what the viewport for the LithoView may be.
 */
boolean needsRemount(){
  assertMainThread();
  return mNeedsRemount;
}

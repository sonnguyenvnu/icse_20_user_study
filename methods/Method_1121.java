/** 
 * Finishes transition immediately.
 */
public void finishTransitionImmediately(){
  mTransitionState=TRANSITION_NONE;
  for (int i=0; i < mLayers.length; i++) {
    mAlphas[i]=mIsLayerOn[i] ? 255 : 0;
  }
  invalidateSelf();
}

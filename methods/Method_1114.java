/** 
 * Resets internal state to the initial state.
 */
private void resetInternal(){
  mTransitionState=TRANSITION_NONE;
  Arrays.fill(mStartAlphas,mDefaultLayerAlpha);
  mStartAlphas[0]=255;
  Arrays.fill(mAlphas,mDefaultLayerAlpha);
  mAlphas[0]=255;
  Arrays.fill(mIsLayerOn,mDefaultLayerIsOn);
  mIsLayerOn[0]=true;
}

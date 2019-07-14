/** 
 * Updates the current alphas based on the ratio of the elapsed time and duration.
 * @param ratio
 * @return whether the all layers have reached their target opacity
 */
private boolean updateAlphas(float ratio){
  boolean done=true;
  for (int i=0; i < mLayers.length; i++) {
    int dir=mIsLayerOn[i] ? +1 : -1;
    mAlphas[i]=(int)(mStartAlphas[i] + dir * 255 * ratio);
    if (mAlphas[i] < 0) {
      mAlphas[i]=0;
    }
    if (mAlphas[i] > 255) {
      mAlphas[i]=255;
    }
    if (mIsLayerOn[i] && mAlphas[i] < 255) {
      done=false;
    }
    if (!mIsLayerOn[i] && mAlphas[i] > 0) {
      done=false;
    }
  }
  return done;
}

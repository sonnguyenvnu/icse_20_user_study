/** 
 * Sets whether image should be scaled down inside borders.
 * @param scaleDownInsideBorders
 */
@Override public void setScaleDownInsideBorders(boolean scaleDownInsideBorders){
  if (mScaleDownInsideBorders != scaleDownInsideBorders) {
    mScaleDownInsideBorders=scaleDownInsideBorders;
    mIsPathDirty=true;
    invalidateSelf();
  }
}

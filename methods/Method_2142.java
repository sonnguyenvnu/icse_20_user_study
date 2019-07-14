/** 
 * Calculates the zoom transformation based on the current gesture.
 * @param outTransform the matrix to store the result to
 * @param limitTypes whether to limit translation and/or scale.
 * @return whether or not the transform has been corrected due to limitation
 */
protected boolean calculateGestureTransform(Matrix outTransform,@LimitFlag int limitTypes){
  TransformGestureDetector detector=mGestureDetector;
  boolean transformCorrected=false;
  outTransform.set(mPreviousTransform);
  if (mIsRotationEnabled) {
    float angle=detector.getRotation() * (float)(180 / Math.PI);
    outTransform.postRotate(angle,detector.getPivotX(),detector.getPivotY());
  }
  if (mIsScaleEnabled) {
    float scale=detector.getScale();
    outTransform.postScale(scale,scale,detector.getPivotX(),detector.getPivotY());
  }
  transformCorrected|=limitScale(outTransform,detector.getPivotX(),detector.getPivotY(),limitTypes);
  if (mIsTranslationEnabled) {
    outTransform.postTranslate(detector.getTranslationX(),detector.getTranslationY());
  }
  transformCorrected|=limitTranslation(outTransform,limitTypes);
  return transformCorrected;
}

/** 
 * ???????????????
 * @param scaleCenter mScaleCenter
 * @param scaleBase   mScaleBase
 * @param distance    ????????
 * @param lineCenter  ????????
 * @see #mScaleCenter
 * @see #mScaleBase
 */
private void scale(PointF scaleCenter,float scaleBase,float distance,PointF lineCenter){
  if (!isReady()) {
    return;
  }
  float scale=scaleBase * distance;
  Matrix matrix=MathUtils.matrixTake();
  matrix.postScale(scale,scale,scaleCenter.x,scaleCenter.y);
  matrix.postTranslate(lineCenter.x - scaleCenter.x,lineCenter.y - scaleCenter.y);
  mOuterMatrix.set(matrix);
  MathUtils.matrixGiven(matrix);
  dispatchOuterMatrixChanged();
  invalidate();
}

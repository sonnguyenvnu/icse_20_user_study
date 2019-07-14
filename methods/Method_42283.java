/** 
 * ????????? <p> ????????,???????????. ??????????????????,??????????.
 */
private void scaleEnd(){
  if (!isReady()) {
    return;
  }
  boolean change=false;
  Matrix currentMatrix=MathUtils.matrixTake();
  getCurrentImageMatrix(currentMatrix);
  float currentScale=MathUtils.getMatrixScale(currentMatrix)[0];
  float outerScale=MathUtils.getMatrixScale(mOuterMatrix)[0];
  float displayWidth=getWidth();
  float displayHeight=getHeight();
  float maxScale=getMaxScale();
  float scalePost=1f;
  float postX=0;
  float postY=0;
  if (currentScale > maxScale) {
    scalePost=maxScale / currentScale;
  }
  if (outerScale * scalePost < 1f) {
    scalePost=1f / outerScale;
  }
  if (scalePost != 1f) {
    change=true;
  }
  Matrix testMatrix=MathUtils.matrixTake(currentMatrix);
  testMatrix.postScale(scalePost,scalePost,mLastMovePoint.x,mLastMovePoint.y);
  RectF testBound=MathUtils.rectFTake(0,0,getDrawable().getIntrinsicWidth(),getDrawable().getIntrinsicHeight());
  testMatrix.mapRect(testBound);
  if (testBound.right - testBound.left < displayWidth) {
    postX=displayWidth / 2f - (testBound.right + testBound.left) / 2f;
  }
 else   if (testBound.left > 0) {
    postX=-testBound.left;
  }
 else   if (testBound.right < displayWidth) {
    postX=displayWidth - testBound.right;
  }
  if (testBound.bottom - testBound.top < displayHeight) {
    postY=displayHeight / 2f - (testBound.bottom + testBound.top) / 2f;
  }
 else   if (testBound.top > 0) {
    postY=-testBound.top;
  }
 else   if (testBound.bottom < displayHeight) {
    postY=displayHeight - testBound.bottom;
  }
  if (postX != 0 || postY != 0) {
    change=true;
  }
  if (change) {
    Matrix animEnd=MathUtils.matrixTake(mOuterMatrix);
    animEnd.postScale(scalePost,scalePost,mLastMovePoint.x,mLastMovePoint.y);
    animEnd.postTranslate(postX,postY);
    cancelAllAnimator();
    mScaleAnimator=new ScaleAnimator(mOuterMatrix,animEnd);
    mScaleAnimator.start();
    MathUtils.matrixGiven(animEnd);
  }
  MathUtils.rectFGiven(testBound);
  MathUtils.matrixGiven(testMatrix);
  MathUtils.matrixGiven(currentMatrix);
}

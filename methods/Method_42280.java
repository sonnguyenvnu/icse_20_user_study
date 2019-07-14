/** 
 * ????????? <p> ???????????,??????????????.
 * @param xDiff ????
 * @param yDiff ????
 * @return ???????
 */
private boolean scrollBy(float xDiff,float yDiff){
  if (!isReady()) {
    return false;
  }
  RectF bound=MathUtils.rectFTake();
  getImageBound(bound);
  float displayWidth=getWidth();
  float displayHeight=getHeight();
  if (bound.right - bound.left < displayWidth) {
    xDiff=0;
  }
 else   if (bound.left + xDiff > 0) {
    if (bound.left < 0) {
      xDiff=-bound.left;
    }
 else {
      xDiff=0;
    }
  }
 else   if (bound.right + xDiff < displayWidth) {
    if (bound.right > displayWidth) {
      xDiff=displayWidth - bound.right;
    }
 else {
      xDiff=0;
    }
  }
  if (bound.bottom - bound.top < displayHeight) {
    yDiff=0;
  }
 else   if (bound.top + yDiff > 0) {
    if (bound.top < 0) {
      yDiff=-bound.top;
    }
 else {
      yDiff=0;
    }
  }
 else   if (bound.bottom + yDiff < displayHeight) {
    if (bound.bottom > displayHeight) {
      yDiff=displayHeight - bound.bottom;
    }
 else {
      yDiff=0;
    }
  }
  MathUtils.rectFGiven(bound);
  mOuterMatrix.postTranslate(xDiff,yDiff);
  dispatchOuterMatrixChanged();
  invalidate();
  if (xDiff != 0 || yDiff != 0) {
    return true;
  }
 else {
    return false;
  }
}

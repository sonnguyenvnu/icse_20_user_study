/** 
 * ??????
 * @param canvas ??
 * @param angle  ????
 */
private void drawMinusBtn(Canvas canvas,float angle){
  if (angle != 0) {
    canvas.rotate(angle,mMinusBtnPosition,MAX_HEIGHT / 2);
  }
  canvas.drawCircle(mMinusBtnPosition,MAX_HEIGHT / 2,MAX_HEIGHT / 2 - MAX_HEIGHT / 20,mPaintMinus);
  canvas.drawLine(mMinusBtnPosition - MAX_HEIGHT / 4,MAX_HEIGHT / 2,mMinusBtnPosition + MAX_HEIGHT / 4,MAX_HEIGHT / 2,mPaintMinus);
  if (angle != 0) {
    canvas.rotate(-angle,mMinusBtnPosition,MAX_HEIGHT / 2);
  }
}

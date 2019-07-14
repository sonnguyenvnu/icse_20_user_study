/** 
 * ??????
 * @param canvas ??
 */
private void drawAddBtn(Canvas canvas){
  canvas.drawCircle(MAX_WIDTH - MAX_HEIGHT / 2,MAX_HEIGHT / 2,MAX_HEIGHT / 2,mPaintBg);
  canvas.drawLine(MAX_WIDTH - MAX_HEIGHT / 2,MAX_HEIGHT / 4,MAX_WIDTH - MAX_HEIGHT / 2,MAX_HEIGHT / 4 * 3,mPaintText);
  canvas.drawLine(MAX_WIDTH - MAX_HEIGHT / 2 - MAX_HEIGHT / 4,MAX_HEIGHT / 2,MAX_WIDTH - MAX_HEIGHT / 4,MAX_HEIGHT / 2,mPaintText);
}

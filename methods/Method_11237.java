/** 
 * ????????
 * @param canvas Canvas
 */
private void drawSpiderRadiusLine(Canvas canvas){
  float nextAngle;
  float nextRadians;
  float nextPointX;
  float nextPointY;
  float averageAngle=360 / mSpiderNumber;
  float offsetAngle=averageAngle > 0 && mSpiderNumber % 2 == 0 ? averageAngle / 2 : 0;
  for (int position=0; position < mSpiderNumber; position++) {
    nextAngle=offsetAngle + (position * averageAngle);
    nextRadians=(float)Math.toRadians(nextAngle);
    nextPointX=(float)(center + Math.sin(nextRadians - mRotateAngle) * one_radius);
    nextPointY=(float)(center - Math.cos(nextRadians - mRotateAngle) * one_radius);
    canvas.drawLine(center,center,nextPointX,nextPointY,center_paint);
  }
}

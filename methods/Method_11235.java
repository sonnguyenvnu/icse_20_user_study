/** 
 * ????
 * @param canvas ??
 */
private void drawSpiderName(Canvas canvas){
  float nextAngle;
  float nextRadians;
  float nextPointX;
  float nextPointY;
  float currentRadius;
  float averageAngle=360 / mSpiderNumber;
  float offsetAngle=averageAngle > 0 && mSpiderNumber % 2 == 0 ? averageAngle / 2 : 0;
  for (int position=0; position < mSpiderNumber; position++) {
    currentRadius=(float)(getPaddingTop() + str_rect.height()) + one_radius;
    nextAngle=offsetAngle + (position * averageAngle);
    nextRadians=(float)Math.toRadians(nextAngle);
    String text=mSpiderList.get(position).getSpiderName();
    float textWidth=mSpiderNamePaint.measureText(text);
    Paint.FontMetrics fontMetrics=mSpiderNamePaint.getFontMetrics();
    float textHeight=fontMetrics.descent - fontMetrics.ascent;
    nextPointX=(float)(center + Math.sin(nextRadians - mRotateAngle) * currentRadius - textWidth / 2);
    nextPointY=(float)(center - Math.cos(nextRadians - mRotateAngle) * currentRadius + textHeight / 4);
    canvas.drawText(text,nextPointX,nextPointY,mSpiderNamePaint);
  }
  mPerimeter=2 * Math.PI * one_radius;
}

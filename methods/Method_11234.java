/** 
 * ??????
 */
private void drawSpiderLevel(Canvas canvas){
  Path path=new Path();
  float nextAngle;
  float nextRadians;
  float nextPointX;
  float nextPointY;
  float currentRadius;
  float averageAngle=360 / mSpiderNumber;
  float offsetAngle=averageAngle > 0 && mSpiderNumber % 2 == 0 ? averageAngle / 2 : 0;
  for (int position=0; position < mSpiderNumber; position++) {
    float scale=(mSpiderList.get(position).getSpiderLevel() / mSpiderMaxLevel);
    if (scale >= 1) {
      scale=1;
    }
    currentRadius=scale * one_radius;
    nextAngle=offsetAngle + (position * averageAngle);
    nextRadians=(float)Math.toRadians(nextAngle);
    nextPointX=(float)(center + Math.sin(nextRadians - mRotateAngle) * currentRadius);
    nextPointY=(float)(center - Math.cos(nextRadians - mRotateAngle) * currentRadius);
    if (position == 0) {
      path.moveTo(nextPointX,nextPointY);
    }
 else {
      path.lineTo(nextPointX,nextPointY);
    }
  }
  Paint scorePaint=new Paint();
  scorePaint.setColor(mSpiderLevelColor);
  scorePaint.setStyle(Paint.Style.FILL_AND_STROKE);
  scorePaint.setAntiAlias(true);
  path.close();
  canvas.drawPath(path,scorePaint);
  Paint scoreStrokePaint=null;
  if (mSpiderLevelStroke) {
    if (scoreStrokePaint == null) {
      scoreStrokePaint=new Paint();
      scoreStrokePaint.setColor(mSpiderLevelStrokeColor);
      scoreStrokePaint.setStyle(Paint.Style.STROKE);
      scoreStrokePaint.setAntiAlias(true);
      if (mSpiderLevelStrokeWidth > 0) {
        scoreStrokePaint.setStrokeWidth(mSpiderLevelStrokeWidth);
      }
    }
    canvas.drawPath(path,scoreStrokePaint);
  }
}

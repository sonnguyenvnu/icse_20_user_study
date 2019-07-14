/** 
 * //??????
 * @param canvas ??
 */
private void drawCobweb(Canvas canvas,int index){
  Path path=new Path();
  float nextAngle;
  float nextRadians;
  float nextPointX;
  float nextPointY;
  float currentRadius;
  float averageAngle=360 / mSpiderNumber;
  float offsetAngle=averageAngle > 0 && mSpiderNumber % 2 == 0 ? averageAngle / 2 : 0;
  for (int position=0; position < mSpiderNumber; position++) {
    currentRadius=(index + 1) * one_radius / mSpiderMaxLevel;
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
  path.close();
  canvas.drawPath(path,levelPaintList.get(mSpiderMaxLevel - index - 1));
  Paint scoreStrokePaint=null;
  if (mSpiderLevelStroke) {
    if (scoreStrokePaint == null) {
      scoreStrokePaint=new Paint();
      scoreStrokePaint.setColor(RxImageTool.changeColorAlpha(levelPaintList.get(mSpiderMaxLevel - 1).getColor(),50));
      scoreStrokePaint.setStyle(Paint.Style.STROKE);
      scoreStrokePaint.setAntiAlias(true);
      if (mSpiderLevelStrokeWidth > 0) {
        scoreStrokePaint.setStrokeWidth(mSpiderLevelStrokeWidth);
      }
    }
    canvas.drawPath(path,scoreStrokePaint);
  }
}

protected void drawTitle(Canvas canvas,int alpha){
  if (alpha > 0 && isShowTitle) {
    Path path=new Path();
    float circumference=(float)(Math.PI * (outlineOval.right - outlineOval.left));
    float textAngle=(360 / circumference) * titlePaint.measureText(getTitle());
    float startAngle=mStartAngle + mSweepAngle / 2 - textAngle / 2;
    if (isSingle) {
      path.addArc(outlineOval,startAngle - mSweepAngle / 2,mSweepAngle / 2);
    }
 else {
      path.addArc(outlineOval,startAngle,mSweepAngle);
    }
    titlePaint.setAlpha(alpha);
    canvas.drawTextOnPath(mTitle,path,0,textWidth / 3,titlePaint);
  }
}

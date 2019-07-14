protected void drawOutLine(Canvas canvas){
  float circumference=(float)(Math.PI * (outlineOval.right - outlineOval.left));
  float textAngle=(360 / circumference) * titlePaint.measureText(getTitle());
  float sweepAngle=(mSweepAngle - textAngle - 1 - 1) / 2;
  if (isShowTitle) {
    float leftStartAngle=mStartAngle;
    canvas.drawArc(outlineOval,leftStartAngle,sweepAngle,false,outlinePaint);
    float rightStartAngle=mStartAngle + mSweepAngle - sweepAngle;
    canvas.drawArc(outlineOval,rightStartAngle,sweepAngle,false,outlinePaint);
  }
 else {
    canvas.drawArc(outlineOval,mStartAngle,mSweepAngle,false,outlinePaint);
  }
}

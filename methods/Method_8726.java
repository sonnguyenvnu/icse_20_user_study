public void onDraw(Canvas canvas){
  if (backColor != 0 && animatedProgressValue != 1) {
    progressPaint.setColor(backColor);
    progressPaint.setAlpha((int)(255 * animatedAlphaValue));
    int start=(int)(getWidth() * animatedProgressValue);
    canvas.drawRect(start,0,getWidth(),getHeight(),progressPaint);
  }
  progressPaint.setColor(progressColor);
  progressPaint.setAlpha((int)(255 * animatedAlphaValue));
  canvas.drawRect(0,0,getWidth() * animatedProgressValue,getHeight(),progressPaint);
  updateAnimation();
}

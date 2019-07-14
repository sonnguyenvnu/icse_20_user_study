@Override protected void onDraw(Canvas canvas){
  if (lengthText != null && getMeasuredHeight() > AndroidUtilities.dp(48)) {
    int width=(int)Math.ceil(lengthTextPaint.measureText(lengthText));
    int x=(AndroidUtilities.dp(56) - width) / 2;
    canvas.drawText(lengthText,x,getMeasuredHeight() - AndroidUtilities.dp(48),lengthTextPaint);
    if (animationProgress < 1.0f) {
      animationProgress+=17.0f / 120.0f;
      invalidate();
      if (animationProgress >= 1.0f) {
        animationProgress=1.0f;
      }
      lengthTextPaint.setAlpha((int)(255 * animationProgress));
    }
  }
 else {
    lengthTextPaint.setAlpha(0);
    animationProgress=0.0f;
  }
}

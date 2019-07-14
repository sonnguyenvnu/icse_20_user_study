@Override protected void onDraw(Canvas canvas){
  int y=(getMeasuredHeight() - thumbSize) / 2;
  int thumbX=(int)((getMeasuredWidth() - thumbSize) * progress);
  canvas.drawRect(thumbSize / 2,getMeasuredHeight() / 2 - AndroidUtilities.dp(1),getMeasuredWidth() - thumbSize / 2,getMeasuredHeight() / 2 + AndroidUtilities.dp(1),innerPaint);
  if (minValue == 0) {
    canvas.drawRect(thumbSize / 2,getMeasuredHeight() / 2 - AndroidUtilities.dp(1),thumbX,getMeasuredHeight() / 2 + AndroidUtilities.dp(1),outerPaint);
  }
 else {
    if (progress > 0.5f) {
      canvas.drawRect(getMeasuredWidth() / 2 - AndroidUtilities.dp(1),(getMeasuredHeight() - thumbSize) / 2,getMeasuredWidth() / 2,(getMeasuredHeight() + thumbSize) / 2,outerPaint);
      canvas.drawRect(getMeasuredWidth() / 2,getMeasuredHeight() / 2 - AndroidUtilities.dp(1),thumbX,getMeasuredHeight() / 2 + AndroidUtilities.dp(1),outerPaint);
    }
 else {
      canvas.drawRect(getMeasuredWidth() / 2,(getMeasuredHeight() - thumbSize) / 2,getMeasuredWidth() / 2 + AndroidUtilities.dp(1),(getMeasuredHeight() + thumbSize) / 2,outerPaint);
      canvas.drawRect(thumbX,getMeasuredHeight() / 2 - AndroidUtilities.dp(1),getMeasuredWidth() / 2,getMeasuredHeight() / 2 + AndroidUtilities.dp(1),outerPaint);
    }
  }
  canvas.drawCircle(thumbX + thumbSize / 2,y + thumbSize / 2,thumbSize / 2,outerPaint);
}

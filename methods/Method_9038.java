@Override protected void onDraw(Canvas canvas){
  int y=(getMeasuredHeight() - thumbHeight) / 2;
  canvas.drawRect(thumbWidth / 2,getMeasuredHeight() / 2 - AndroidUtilities.dp(1),getMeasuredWidth() - thumbWidth / 2,getMeasuredHeight() / 2 + AndroidUtilities.dp(1),innerPaint1);
  if (bufferedProgress > 0) {
    canvas.drawRect(thumbWidth / 2,getMeasuredHeight() / 2 - AndroidUtilities.dp(1),thumbWidth / 2 + bufferedProgress * (getMeasuredWidth() - thumbWidth),getMeasuredHeight() / 2 + AndroidUtilities.dp(1),innerPaint1);
  }
  canvas.drawRect(thumbWidth / 2,getMeasuredHeight() / 2 - AndroidUtilities.dp(1),thumbWidth / 2 + thumbX,getMeasuredHeight() / 2 + AndroidUtilities.dp(1),outerPaint1);
  canvas.drawCircle(thumbX + thumbWidth / 2,y + thumbHeight / 2,AndroidUtilities.dp(pressed ? 8 : 6),outerPaint1);
}

@Override protected void onDraw(Canvas canvas){
  if (needDivider) {
    canvas.drawLine(AndroidUtilities.dp(72),getHeight() - 1,getWidth(),getHeight() - 1,Theme.dividerPaint);
  }
}

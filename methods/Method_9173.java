@Override protected void onDraw(Canvas canvas){
  canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight() - AndroidUtilities.dp(10),backgroundPaint);
}

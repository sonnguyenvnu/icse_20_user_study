@Override protected void onDraw(Canvas canvas){
  int y=(getMeasuredHeight() - thumbHeight) / 2;
  int thumbX=(int)((getMeasuredWidth() - thumbWidth) * progress);
  canvas.drawRect(thumbWidth / 2,getMeasuredHeight() / 2 - AndroidUtilities.dp(1),getMeasuredWidth() - thumbWidth / 2,getMeasuredHeight() / 2 + AndroidUtilities.dp(1),paint);
  canvas.drawCircle(thumbX + thumbWidth / 2,y + thumbHeight / 2,thumbWidth / 2,paint2);
}

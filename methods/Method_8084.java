@Override protected void onDraw(Canvas canvas){
  if (needDivider) {
    canvas.drawLine(AndroidUtilities.dp(LocaleController.isRTL ? 0 : 60),getHeight() - 1,getMeasuredWidth() - AndroidUtilities.dp(LocaleController.isRTL ? 60 : 0),getHeight() - 1,Theme.dividerPaint);
  }
}

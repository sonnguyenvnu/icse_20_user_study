@Override protected void onDraw(Canvas canvas){
  if (needDivider) {
    canvas.drawLine(LocaleController.isRTL ? 0 : AndroidUtilities.dp(68),getMeasuredHeight() - 1,getMeasuredWidth() - (LocaleController.isRTL ? AndroidUtilities.dp(68) : 0),getMeasuredHeight() - 1,Theme.dividerPaint);
  }
}

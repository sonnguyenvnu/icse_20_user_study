@Override protected void onDraw(Canvas canvas){
  if (needDivider) {
    canvas.drawLine(LocaleController.isRTL ? 0 : AndroidUtilities.dp(19),getMeasuredHeight() - 1,getMeasuredWidth() - (LocaleController.isRTL ? AndroidUtilities.dp(19) : 0),getMeasuredHeight() - 1,Theme.dividerPaint);
  }
}

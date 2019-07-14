@Override protected void onDraw(Canvas canvas){
  if (needDivider) {
    canvas.drawLine(LocaleController.isRTL ? 0 : AndroidUtilities.dp(left),getMeasuredHeight() - 1,getMeasuredWidth() - (LocaleController.isRTL ? AndroidUtilities.dp(left) : 0),getMeasuredHeight() - 1,Theme.dividerPaint);
  }
}

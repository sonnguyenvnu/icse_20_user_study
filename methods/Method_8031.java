@Override protected void onDraw(Canvas canvas){
  if (UserConfig.getActivatedAccountsCount() <= 1 || !NotificationsController.getInstance(accountNumber).showBadgeNumber) {
    return;
  }
  int counter=NotificationsController.getInstance(accountNumber).getTotalUnreadCount();
  if (counter <= 0) {
    return;
  }
  String text=String.format("%d",counter);
  int countTop=AndroidUtilities.dp(12.5f);
  int textWidth=(int)Math.ceil(Theme.dialogs_countTextPaint.measureText(text));
  int countWidth=Math.max(AndroidUtilities.dp(10),textWidth);
  int countLeft=getMeasuredWidth() - countWidth - AndroidUtilities.dp(25);
  int x=countLeft - AndroidUtilities.dp(5.5f);
  rect.set(x,countTop,x + countWidth + AndroidUtilities.dp(14),countTop + AndroidUtilities.dp(23));
  canvas.drawRoundRect(rect,11.5f * AndroidUtilities.density,11.5f * AndroidUtilities.density,Theme.dialogs_countPaint);
  canvas.drawText(text,rect.left + (rect.width() - textWidth) / 2,countTop + AndroidUtilities.dp(16),Theme.dialogs_countTextPaint);
}

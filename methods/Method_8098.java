@Override protected void onDraw(Canvas canvas){
  if (titleLayout != null) {
    canvas.save();
    canvas.translate(AndroidUtilities.dp(LocaleController.isRTL ? 8 : AndroidUtilities.leftBaseline),titleY);
    titleLayout.draw(canvas);
    canvas.restore();
  }
  if (descriptionLayout != null) {
    Theme.chat_contextResult_descriptionTextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
    canvas.save();
    canvas.translate(AndroidUtilities.dp(LocaleController.isRTL ? 8 : AndroidUtilities.leftBaseline),descriptionY);
    descriptionLayout.draw(canvas);
    canvas.restore();
  }
  radialProgress.setProgressColor(Theme.getColor(buttonPressed ? Theme.key_chat_inAudioSelectedProgress : Theme.key_chat_inAudioProgress));
  radialProgress.draw(canvas);
  if (needDivider) {
    canvas.drawLine(AndroidUtilities.dp(72),getHeight() - 1,getWidth() - getPaddingRight(),getHeight() - 1,Theme.dividerPaint);
  }
}

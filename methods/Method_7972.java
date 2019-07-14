public void drawRoundProgress(Canvas canvas){
  rect.set(photoImage.getImageX() + AndroidUtilities.dpf2(1.5f),photoImage.getImageY() + AndroidUtilities.dpf2(1.5f),photoImage.getImageX2() - AndroidUtilities.dpf2(1.5f),photoImage.getImageY2() - AndroidUtilities.dpf2(1.5f));
  canvas.drawArc(rect,-90,360 * currentMessageObject.audioProgress,false,Theme.chat_radialProgressPaint);
}

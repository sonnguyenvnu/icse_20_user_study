public void setDrawBackgroundAsArc(int type){
  drawBackgroundAsArc=type;
  if (type == 4 || type == 5) {
    backgroundPaint.setStrokeWidth(AndroidUtilities.dp(1.9f));
    if (type == 5) {
      checkPaint.setStrokeWidth(AndroidUtilities.dp(1.5f));
    }
  }
 else   if (type == 3) {
    backgroundPaint.setStrokeWidth(AndroidUtilities.dp(1.2f));
  }
 else   if (type != 0) {
    backgroundPaint.setStrokeWidth(AndroidUtilities.dp(1.5f));
  }
}

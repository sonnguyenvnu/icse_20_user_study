public void setRoundFrames(boolean value){
  isRoundFrames=value;
  if (isRoundFrames) {
    rect1=new Rect(AndroidUtilities.dp(14),AndroidUtilities.dp(14),AndroidUtilities.dp(14 + 28),AndroidUtilities.dp(14 + 28));
    rect2=new Rect();
  }
}

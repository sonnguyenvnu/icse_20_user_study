public void setMini(boolean value){
  isMini=value;
  paint.setStrokeWidth(AndroidUtilities.dp(isMini ? 2 : 3));
}

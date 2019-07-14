public boolean isPointInsideAvatar(float x,float y){
  if (!LocaleController.isRTL) {
    return x >= 0 && x < AndroidUtilities.dp(60);
  }
 else {
    return x >= getMeasuredWidth() - AndroidUtilities.dp(60) && x < getMeasuredWidth();
  }
}

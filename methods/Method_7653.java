private void cancelSheetAnimation(){
  if (currentSheetAnimation != null) {
    currentSheetAnimation.cancel();
    currentSheetAnimation=null;
  }
}

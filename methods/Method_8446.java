private void resizeForTopView(boolean show){
  LayoutParams layoutParams=(LayoutParams)textFieldContainer.getLayoutParams();
  layoutParams.topMargin=AndroidUtilities.dp(2) + (show ? topView.getLayoutParams().height : 0);
  textFieldContainer.setLayoutParams(layoutParams);
  setMinimumHeight(AndroidUtilities.dp(51) + (show ? topView.getLayoutParams().height : 0));
  if (stickersExpanded) {
    if (searchingType == 0) {
      setStickersExpanded(false,true,false);
    }
 else {
      checkStickresExpandHeight();
    }
  }
}

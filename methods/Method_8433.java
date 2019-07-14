public void setPanelHeight(int height){
  panelHeight=height;
  if (isFullSize && botButtons != null && botButtons.rows.size() != 0) {
    buttonHeight=!isFullSize ? 42 : (int)Math.max(42,(panelHeight - AndroidUtilities.dp(30) - (botButtons.rows.size() - 1) * AndroidUtilities.dp(10)) / botButtons.rows.size() / AndroidUtilities.density);
    int count=container.getChildCount();
    int newHeight=AndroidUtilities.dp(buttonHeight);
    for (int a=0; a < count; a++) {
      View v=container.getChildAt(a);
      LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)v.getLayoutParams();
      if (layoutParams.height != newHeight) {
        layoutParams.height=newHeight;
        v.setLayoutParams(layoutParams);
      }
    }
  }
}

public void setExtraHeight(int value){
  extraHeight=value;
  if (actionMode != null) {
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)actionMode.getLayoutParams();
    layoutParams.bottomMargin=extraHeight;
    actionMode.setLayoutParams(layoutParams);
  }
}

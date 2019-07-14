public void setHeight(int value){
  textView.setMinHeight(AndroidUtilities.dp(height) - ((LayoutParams)textView.getLayoutParams()).topMargin);
}

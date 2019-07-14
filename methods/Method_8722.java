public static ScrollView.LayoutParams createScroll(int width,int height,int gravity,float leftMargin,float topMargin,float rightMargin,float bottomMargin){
  ScrollView.LayoutParams layoutParams=new ScrollView.LayoutParams(getSize(width),getSize(height),gravity);
  layoutParams.leftMargin=AndroidUtilities.dp(leftMargin);
  layoutParams.topMargin=AndroidUtilities.dp(topMargin);
  layoutParams.rightMargin=AndroidUtilities.dp(rightMargin);
  layoutParams.bottomMargin=AndroidUtilities.dp(bottomMargin);
  return layoutParams;
}

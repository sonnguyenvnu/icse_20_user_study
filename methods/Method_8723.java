public static FrameLayout.LayoutParams createFrame(int width,float height,int gravity,float leftMargin,float topMargin,float rightMargin,float bottomMargin){
  FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams(getSize(width),getSize(height),gravity);
  layoutParams.setMargins(AndroidUtilities.dp(leftMargin),AndroidUtilities.dp(topMargin),AndroidUtilities.dp(rightMargin),AndroidUtilities.dp(bottomMargin));
  return layoutParams;
}

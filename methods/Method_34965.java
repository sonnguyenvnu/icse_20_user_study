private void setMaxDimensions(View itemView){
  int screenWidth;
  int screenHeight;
  screenWidth=deviceUtils.getScreenWidth();
  screenHeight=deviceUtils.getScreenHeight();
  if (screenWidth > screenHeight) {
    screenHeight=deviceUtils.getScreenWidth();
    screenWidth=deviceUtils.getScreenHeight();
  }
  maxHeight=(int)(screenHeight * .55f);
  int margin=itemView.getContext().getResources().getDimensionPixelSize(R.dimen.post_horizontal_margin);
  maxWidth=screenWidth - (2 * margin);
}

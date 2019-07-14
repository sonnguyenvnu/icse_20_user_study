public static ScrollView.LayoutParams createScroll(int width,int height,int gravity){
  return new ScrollView.LayoutParams(getSize(width),getSize(height),gravity);
}

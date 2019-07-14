@RequiresApi(api=Build.VERSION_CODES.JELLY_BEAN) public void setOrientation(GradientDrawable.Orientation orientation){
  this.orientation=orientation;
  getGradientDrawable().setOrientation(orientation);
}

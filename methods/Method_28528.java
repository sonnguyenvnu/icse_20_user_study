private void setScrollerHeight(float y){
  int handleHeight=scrollerView.getHeight();
  scrollerView.setY(getValueInRange(height - handleHeight,(int)(y - handleHeight / 2)));
}

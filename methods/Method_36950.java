public void setIndicatorPos(String isInside){
  if ("inside".equals(isInside)) {
    isIndicatorOutside=false;
  }
 else   if ("outside".equals(isInside)) {
    isIndicatorOutside=true;
  }
 else {
    isIndicatorOutside=false;
  }
}

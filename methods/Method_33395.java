/** 
 * resize the svg to this size while keeping the width/height ratio
 * @param size in pixel
 */
private void setSizeRatio(double size){
  double width=widthHeightRatio * size;
  double height=size / widthHeightRatio;
  if (width <= size) {
    setSize(width,size);
  }
 else   if (height <= size) {
    setSize(size,height);
  }
 else {
    setSize(size,size);
  }
}

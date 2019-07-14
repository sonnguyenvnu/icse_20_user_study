/** 
 * update the size of error container and its clip
 * @param w
 * @param errorContainerHeight
 */
private void updateErrorContainerSize(double w,double errorContainerHeight){
  errorContainerClip.setWidth(w);
  errorContainerClip.setHeight(errorContainerHeight);
  resize(w,errorContainerHeight);
}

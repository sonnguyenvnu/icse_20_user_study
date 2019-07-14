/** 
 * Maps array of 2D points from image-relative to view-absolute coordinates. This does NOT take into account the zoomable transformation. Points are represented by float array of [x0, y0, x1, y1, ...].
 * @param destPoints destination array (may be the same as source array)
 * @param srcPoints source array
 * @param numPoints number of points to map
 */
private void mapRelativeToAbsolute(float[] destPoints,float[] srcPoints,int numPoints){
  for (int i=0; i < numPoints; i++) {
    destPoints[i * 2 + 0]=srcPoints[i * 2 + 0] * mImageBounds.width() + mImageBounds.left;
    destPoints[i * 2 + 1]=srcPoints[i * 2 + 1] * mImageBounds.height() + mImageBounds.top;
  }
}

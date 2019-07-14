/** 
 * Maps array of 2D points from view-absolute to image-relative coordinates. This does NOT take into account the zoomable transformation. Points are represented by a float array of [x0, y0, x1, y1, ...].
 * @param destPoints destination array (may be the same as source array)
 * @param srcPoints source array
 * @param numPoints number of points to map
 */
private void mapAbsoluteToRelative(float[] destPoints,float[] srcPoints,int numPoints){
  for (int i=0; i < numPoints; i++) {
    destPoints[i * 2 + 0]=(srcPoints[i * 2 + 0] - mImageBounds.left) / mImageBounds.width();
    destPoints[i * 2 + 1]=(srcPoints[i * 2 + 1] - mImageBounds.top) / mImageBounds.height();
  }
}

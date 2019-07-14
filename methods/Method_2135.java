/** 
 * Gets the matrix that transforms image-relative coordinates to view-absolute coordinates. The zoomable transformation is taken into account.
 */
public void getImageRelativeToViewAbsoluteTransform(Matrix outMatrix){
  outMatrix.setRectToRect(IDENTITY_RECT,mTransformedImageBounds,Matrix.ScaleToFit.FILL);
}

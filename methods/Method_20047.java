/** 
 * Sets the camera attributes for size and facing direction, which informs how to transform image coordinates later.
 */
public void setCameraInfo(int previewWidth,int previewHeight,int facing){
synchronized (lock) {
    this.previewWidth=previewWidth;
    this.previewHeight=previewHeight;
    this.facing=facing;
  }
  postInvalidate();
}

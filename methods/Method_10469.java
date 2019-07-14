/** 
 * Configures the transform matrix for TextureView based on  {@link #mDisplayOrientation} andthe surface size.
 */
void configureTransform(){
  Matrix matrix=new Matrix();
  if (mDisplayOrientation % 180 == 90) {
    final int width=getWidth();
    final int height=getHeight();
    matrix.setPolyToPoly(new float[]{0.f,0.f,width,0.f,0.f,height,width,height},0,mDisplayOrientation == 90 ? new float[]{0.f,height,0.f,0.f,width,height,width,0.f} : new float[]{width,0.f,width,height,0.f,0.f,0.f,height},0,4);
  }
 else   if (mDisplayOrientation == 180) {
    matrix.postRotate(180,getWidth() / 2,getHeight() / 2);
  }
  mTextureView.setTransform(matrix);
}

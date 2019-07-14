private void updatePaint(){
  if (mLastBitmap == null || mLastBitmap.get() != mBitmap) {
    mLastBitmap=new WeakReference<>(mBitmap);
    mPaint.setShader(new BitmapShader(mBitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP));
    mIsShaderTransformDirty=true;
  }
  if (mIsShaderTransformDirty) {
    mPaint.getShader().setLocalMatrix(mTransform);
    mIsShaderTransformDirty=false;
  }
  mPaint.setFilterBitmap(getPaintFilterBitmap());
}

public void setBitmap(Bitmap bitmap,boolean sideward,boolean fform){
  if (bitmap == null || bitmap.isRecycled()) {
    return;
  }
  freeform=fform;
  float aspectRatio;
  if (sideward) {
    aspectRatio=((float)bitmap.getHeight()) / ((float)bitmap.getWidth());
  }
 else {
    aspectRatio=((float)bitmap.getWidth()) / ((float)bitmap.getHeight());
  }
  if (!freeform) {
    aspectRatio=1.0f;
    lockAspectRatio=1.0f;
  }
  setActualRect(aspectRatio);
}

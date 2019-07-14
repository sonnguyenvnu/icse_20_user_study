@Override public void process(Bitmap bitmap){
  NativeBlurFilter.iterativeBoxBlur(bitmap,mIterations,mBlurRadius);
}

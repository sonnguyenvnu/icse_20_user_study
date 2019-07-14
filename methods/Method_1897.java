@Override public void process(Bitmap bitmap){
  NativeRoundingFilter.toCircle(bitmap,mEnableAntiAliasing);
}

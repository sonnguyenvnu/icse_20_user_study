@SuppressLint("Range") private void applyBackendProperties(AnimationBackend backend){
  if (mBounds != null) {
    backend.setBounds(mBounds);
  }
  if (mAlpha >= 0 && mAlpha <= 255) {
    backend.setAlpha(mAlpha);
  }
  if (mColorFilter != null) {
    backend.setColorFilter(mColorFilter);
  }
}

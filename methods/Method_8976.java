public float getAlpha(){
  return previousDrawable != null || currentDrawable != null ? animatedAlphaValue : 0.0f;
}

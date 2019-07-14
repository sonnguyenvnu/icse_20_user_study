protected void setTextureModeImpl(int mode){
  if (textureMode == mode)   return;
  textureMode=mode;
  if (image != null) {
    float uFactor=image.width;
    float vFactor=image.height;
    if (textureMode == NORMAL) {
      uFactor=1.0f / uFactor;
      vFactor=1.0f / vFactor;
    }
    scaleTextureUV(uFactor,vFactor);
  }
}

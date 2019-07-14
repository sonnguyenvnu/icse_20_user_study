protected void checkTexture(Texture tex){
  if (!tex.colorBuffer() && (tex.usingMipmaps == hints[DISABLE_TEXTURE_MIPMAPS] || tex.currentSampling() != textureSampling)) {
    if (hints[DISABLE_TEXTURE_MIPMAPS]) {
      tex.usingMipmaps(false,textureSampling);
    }
 else {
      tex.usingMipmaps(true,textureSampling);
    }
  }
  if ((tex.usingRepeat && textureWrap == CLAMP) || (!tex.usingRepeat && textureWrap == REPEAT)) {
    if (textureWrap == CLAMP) {
      tex.usingRepeat(false);
    }
 else {
      tex.usingRepeat(true);
    }
  }
}

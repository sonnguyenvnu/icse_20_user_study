public int currentSampling(){
  if (glMagFilter == PGL.NEAREST && glMinFilter == PGL.NEAREST) {
    return POINT;
  }
 else   if (glMagFilter == PGL.NEAREST && glMinFilter == (PGL.MIPMAPS_ENABLED ? PGL.LINEAR_MIPMAP_NEAREST : PGL.LINEAR)) {
    return LINEAR;
  }
 else   if (glMagFilter == PGL.LINEAR && glMinFilter == (PGL.MIPMAPS_ENABLED ? PGL.LINEAR_MIPMAP_NEAREST : PGL.LINEAR)) {
    return BILINEAR;
  }
 else   if (glMagFilter == PGL.LINEAR && glMinFilter == PGL.LINEAR_MIPMAP_LINEAR) {
    return TRILINEAR;
  }
 else {
    return -1;
  }
}

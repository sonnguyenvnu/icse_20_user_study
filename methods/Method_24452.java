public void usingMipmaps(boolean mipmaps,int sampling){
  int glMagFilter0=glMagFilter;
  int glMinFilter0=glMinFilter;
  if (mipmaps) {
    if (sampling == POINT) {
      glMagFilter=PGL.NEAREST;
      glMinFilter=PGL.NEAREST;
      usingMipmaps=false;
    }
 else     if (sampling == LINEAR) {
      glMagFilter=PGL.NEAREST;
      glMinFilter=PGL.MIPMAPS_ENABLED ? PGL.LINEAR_MIPMAP_NEAREST : PGL.LINEAR;
      usingMipmaps=true;
    }
 else     if (sampling == BILINEAR) {
      glMagFilter=PGL.LINEAR;
      glMinFilter=PGL.MIPMAPS_ENABLED ? PGL.LINEAR_MIPMAP_NEAREST : PGL.LINEAR;
      usingMipmaps=true;
    }
 else     if (sampling == TRILINEAR) {
      glMagFilter=PGL.LINEAR;
      glMinFilter=PGL.MIPMAPS_ENABLED ? PGL.LINEAR_MIPMAP_LINEAR : PGL.LINEAR;
      usingMipmaps=true;
    }
 else {
      throw new RuntimeException("Unknown texture filtering mode");
    }
  }
 else {
    usingMipmaps=false;
    if (sampling == POINT) {
      glMagFilter=PGL.NEAREST;
      glMinFilter=PGL.NEAREST;
    }
 else     if (sampling == LINEAR) {
      glMagFilter=PGL.NEAREST;
      glMinFilter=PGL.LINEAR;
    }
 else     if (sampling == BILINEAR || sampling == TRILINEAR) {
      glMagFilter=PGL.LINEAR;
      glMinFilter=PGL.LINEAR;
    }
 else {
      throw new RuntimeException("Unknown texture filtering mode");
    }
  }
  if (glMagFilter0 != glMagFilter || glMinFilter0 != glMinFilter) {
    bind();
    pgl.texParameteri(glTarget,PGL.TEXTURE_MIN_FILTER,glMinFilter);
    pgl.texParameteri(glTarget,PGL.TEXTURE_MAG_FILTER,glMagFilter);
    if (usingMipmaps) {
      if (PGraphicsOpenGL.autoMipmapGenSupported) {
        pgl.generateMipmap(glTarget);
      }
 else {
        manualMipmap();
      }
    }
    unbind();
  }
}

public Parameters getParameters(){
  Parameters res=new Parameters();
  if (glTarget == PGL.TEXTURE_2D) {
    res.target=TEX2D;
  }
  if (glFormat == PGL.RGB) {
    res.format=RGB;
  }
 else   if (glFormat == PGL.RGBA) {
    res.format=ARGB;
  }
 else   if (glFormat == PGL.ALPHA) {
    res.format=ALPHA;
  }
  if (glMagFilter == PGL.NEAREST && glMinFilter == PGL.NEAREST) {
    res.sampling=POINT;
    res.mipmaps=false;
  }
 else   if (glMagFilter == PGL.NEAREST && glMinFilter == PGL.LINEAR) {
    res.sampling=LINEAR;
    res.mipmaps=false;
  }
 else   if (glMagFilter == PGL.NEAREST && glMinFilter == PGL.LINEAR_MIPMAP_NEAREST) {
    res.sampling=LINEAR;
    res.mipmaps=true;
  }
 else   if (glMagFilter == PGL.LINEAR && glMinFilter == PGL.LINEAR) {
    res.sampling=BILINEAR;
    res.mipmaps=false;
  }
 else   if (glMagFilter == PGL.LINEAR && glMinFilter == PGL.LINEAR_MIPMAP_NEAREST) {
    res.sampling=BILINEAR;
    res.mipmaps=true;
  }
 else   if (glMagFilter == PGL.LINEAR && glMinFilter == PGL.LINEAR_MIPMAP_LINEAR) {
    res.sampling=TRILINEAR;
    res.mipmaps=true;
  }
  if (glWrapS == PGL.CLAMP_TO_EDGE) {
    res.wrapU=CLAMP;
  }
 else   if (glWrapS == PGL.REPEAT) {
    res.wrapU=REPEAT;
  }
  if (glWrapT == PGL.CLAMP_TO_EDGE) {
    res.wrapV=CLAMP;
  }
 else   if (glWrapT == PGL.REPEAT) {
    res.wrapV=REPEAT;
  }
  return res;
}

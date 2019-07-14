/** 
 * Allocates the opengl texture object.
 */
protected void allocate(){
  dispose();
  boolean enabledTex=false;
  if (!pgl.texturingIsEnabled(glTarget)) {
    pgl.enableTexturing(glTarget);
    enabledTex=true;
  }
  context=pgl.getCurrentContext();
  glres=new GLResourceTexture(this);
  pgl.bindTexture(glTarget,glName);
  pgl.texParameteri(glTarget,PGL.TEXTURE_MIN_FILTER,glMinFilter);
  pgl.texParameteri(glTarget,PGL.TEXTURE_MAG_FILTER,glMagFilter);
  pgl.texParameteri(glTarget,PGL.TEXTURE_WRAP_S,glWrapS);
  pgl.texParameteri(glTarget,PGL.TEXTURE_WRAP_T,glWrapT);
  if (PGraphicsOpenGL.anisoSamplingSupported) {
    pgl.texParameterf(glTarget,PGL.TEXTURE_MAX_ANISOTROPY,PGraphicsOpenGL.maxAnisoAmount);
  }
  pgl.texImage2D(glTarget,0,glFormat,glWidth,glHeight,0,PGL.RGBA,PGL.UNSIGNED_BYTE,null);
  pgl.initTexture(glTarget,PGL.RGBA,width,height);
  pgl.bindTexture(glTarget,0);
  if (enabledTex) {
    pgl.disableTexturing(glTarget);
  }
  bound=false;
}

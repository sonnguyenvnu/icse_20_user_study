protected void initOffscreen(){
  loadTextureImpl(textureSampling,false);
  FrameBuffer ofb=offscreenFramebuffer;
  FrameBuffer mfb=multisampleFramebuffer;
  if (ofb != null) {
    ofb.dispose();
    ofb=null;
  }
  if (mfb != null) {
    mfb.dispose();
    mfb=null;
  }
  boolean packed=depthBits == 24 && stencilBits == 8 && packedDepthStencilSupported;
  if (PGraphicsOpenGL.fboMultisampleSupported && 1 < PGL.smoothToSamples(smooth)) {
    mfb=new FrameBuffer(this,texture.glWidth,texture.glHeight,PGL.smoothToSamples(smooth),0,depthBits,stencilBits,packed,false);
    mfb.clear();
    multisampleFramebuffer=mfb;
    offscreenMultisample=true;
    if (hints[ENABLE_BUFFER_READING]) {
      ofb=new FrameBuffer(this,texture.glWidth,texture.glHeight,1,1,depthBits,stencilBits,packed,false);
    }
 else {
      ofb=new FrameBuffer(this,texture.glWidth,texture.glHeight,1,1,0,0,false,false);
    }
  }
 else {
    smooth=0;
    ofb=new FrameBuffer(this,texture.glWidth,texture.glHeight,1,1,depthBits,stencilBits,packed,false);
    offscreenMultisample=false;
  }
  ofb.setColorBuffer(texture);
  ofb.clear();
  offscreenFramebuffer=ofb;
  initialized=true;
}

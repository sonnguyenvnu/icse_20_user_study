private void createDepthAndStencilBuffer(boolean multisample,int depthBits,int stencilBits,boolean packed){
  if (packed && depthBits == 24 && stencilBits == 8) {
    IntBuffer depthStencilBuf=multisample ? glMultiDepthStencil : glDepthStencil;
    genRenderbuffers(1,depthStencilBuf);
    bindRenderbuffer(RENDERBUFFER,depthStencilBuf.get(0));
    if (multisample) {
      renderbufferStorageMultisample(RENDERBUFFER,numSamples,DEPTH24_STENCIL8,fboWidth,fboHeight);
    }
 else {
      renderbufferStorage(RENDERBUFFER,DEPTH24_STENCIL8,fboWidth,fboHeight);
    }
    framebufferRenderbuffer(FRAMEBUFFER,DEPTH_ATTACHMENT,RENDERBUFFER,depthStencilBuf.get(0));
    framebufferRenderbuffer(FRAMEBUFFER,STENCIL_ATTACHMENT,RENDERBUFFER,depthStencilBuf.get(0));
  }
 else {
    if (0 < depthBits) {
      int depthComponent=DEPTH_COMPONENT16;
      if (depthBits == 32) {
        depthComponent=DEPTH_COMPONENT32;
      }
 else       if (depthBits == 24) {
        depthComponent=DEPTH_COMPONENT24;
      }
 else       if (depthBits == 16) {
        depthComponent=DEPTH_COMPONENT16;
      }
      IntBuffer depthBuf=multisample ? glMultiDepth : glDepth;
      genRenderbuffers(1,depthBuf);
      bindRenderbuffer(RENDERBUFFER,depthBuf.get(0));
      if (multisample) {
        renderbufferStorageMultisample(RENDERBUFFER,numSamples,depthComponent,fboWidth,fboHeight);
      }
 else {
        renderbufferStorage(RENDERBUFFER,depthComponent,fboWidth,fboHeight);
      }
      framebufferRenderbuffer(FRAMEBUFFER,DEPTH_ATTACHMENT,RENDERBUFFER,depthBuf.get(0));
    }
    if (0 < stencilBits) {
      int stencilIndex=STENCIL_INDEX1;
      if (stencilBits == 8) {
        stencilIndex=STENCIL_INDEX8;
      }
 else       if (stencilBits == 4) {
        stencilIndex=STENCIL_INDEX4;
      }
 else       if (stencilBits == 1) {
        stencilIndex=STENCIL_INDEX1;
      }
      IntBuffer stencilBuf=multisample ? glMultiStencil : glStencil;
      genRenderbuffers(1,stencilBuf);
      bindRenderbuffer(RENDERBUFFER,stencilBuf.get(0));
      if (multisample) {
        renderbufferStorageMultisample(RENDERBUFFER,numSamples,stencilIndex,fboWidth,fboHeight);
      }
 else {
        renderbufferStorage(RENDERBUFFER,stencilIndex,fboWidth,fboHeight);
      }
      framebufferRenderbuffer(FRAMEBUFFER,STENCIL_ATTACHMENT,RENDERBUFFER,stencilBuf.get(0));
    }
  }
}

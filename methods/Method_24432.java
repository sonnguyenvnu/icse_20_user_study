public void setSmooth(int level){
  pgl.reqNumSamples=level;
  GLCapabilities caps=new GLCapabilities(profile);
  caps.setAlphaBits(PGL.REQUESTED_ALPHA_BITS);
  caps.setDepthBits(PGL.REQUESTED_DEPTH_BITS);
  caps.setStencilBits(PGL.REQUESTED_STENCIL_BITS);
  caps.setSampleBuffers(true);
  caps.setNumSamples(pgl.reqNumSamples);
  caps.setBackgroundOpaque(true);
  caps.setOnscreen(true);
  NativeSurface target=window.getNativeSurface();
  MutableGraphicsConfiguration config=(MutableGraphicsConfiguration)target.getGraphicsConfiguration();
  config.setChosenCapabilities(caps);
}

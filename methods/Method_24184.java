protected void swapOffscreenTextures(){
  FrameBuffer ofb=offscreenFramebuffer;
  if (texture != null && ptexture != null && ofb != null) {
    int temp=texture.glName;
    texture.glName=ptexture.glName;
    ptexture.glName=temp;
    ofb.setColorBuffer(texture);
  }
}

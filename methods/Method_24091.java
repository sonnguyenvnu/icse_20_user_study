protected void restoreGL(){
  blendMode(blendMode);
  if (hints[DISABLE_DEPTH_TEST]) {
    pgl.disable(PGL.DEPTH_TEST);
  }
 else {
    pgl.enable(PGL.DEPTH_TEST);
  }
  pgl.depthFunc(PGL.LEQUAL);
  if (pgl.isES()) {
  }
 else   if (smooth < 1) {
    pgl.disable(PGL.MULTISAMPLE);
  }
 else   if (1 <= smooth) {
    pgl.enable(PGL.MULTISAMPLE);
  }
  if (!pgl.isES()) {
    pgl.disable(PGL.POLYGON_SMOOTH);
  }
  pgl.viewport(viewport.get(0),viewport.get(1),viewport.get(2),viewport.get(3));
  if (clip) {
    pgl.enable(PGL.SCISSOR_TEST);
    pgl.scissor(clipRect[0],clipRect[1],clipRect[2],clipRect[3]);
  }
 else {
    pgl.disable(PGL.SCISSOR_TEST);
  }
  pgl.frontFace(PGL.CW);
  pgl.disable(PGL.CULL_FACE);
  pgl.activeTexture(PGL.TEXTURE0);
  if (hints[DISABLE_DEPTH_MASK]) {
    pgl.depthMask(false);
  }
 else {
    pgl.depthMask(true);
  }
  FrameBuffer fb=getCurrentFB();
  if (fb != null) {
    fb.bind();
    if (drawBufferSupported)     pgl.drawBuffer(fb.getDefaultDrawBuffer());
  }
}

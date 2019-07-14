public void finish(){
  if (noDepth) {
    if (pg.getHint(ENABLE_DEPTH_TEST)) {
      pgl.enable(PGL.DEPTH_TEST);
    }
 else {
      pgl.disable(PGL.DEPTH_TEST);
    }
  }
}

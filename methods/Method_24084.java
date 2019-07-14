@Override public void endDraw(){
  report("top endDraw()");
  if (!drawing) {
    return;
  }
  flush();
  if (primaryGraphics) {
    endOnscreenDraw();
  }
 else {
    endOffscreenDraw();
  }
  if (primaryGraphics) {
    setCurrentPG(null);
  }
 else {
    getPrimaryPG().setCurrentPG();
  }
  drawing=false;
  report("bot endDraw()");
}

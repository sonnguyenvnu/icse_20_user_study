private void drawMarker(Canvas canvas,RectF rect){
  rect.inset(baseSize * 0.225f,baseSize * 0.225f);
  setModeOrColor(pGraph,XFERMODE_CLEAR,backgroundColor);
  canvas.drawOval(rect,pGraph);
  rect.inset(baseSize * 0.1f,baseSize * 0.1f);
  setModeOrColor(pGraph,XFERMODE_SRC,primaryColor);
  canvas.drawOval(rect,pGraph);
  if (isTransparencyEnabled)   pGraph.setXfermode(XFERMODE_SRC);
}

@Override public void endShape(int mode){
  if (openContour) {
    endContour();
    PGraphics.showWarning("Missing endContour() before endShape()");
  }
  if (workPath.getNumCommands() > 0) {
    if (shape == POLYGON) {
      if (mode == CLOSE) {
        workPath.closePath();
      }
      if (auxPath.getNumCommands() > 0) {
        workPath.append(auxPath,false);
      }
      drawShape(workPath);
    }
  }
  shape=0;
  if (adjustedForThinLines) {
    adjustedForThinLines=false;
    translate(-0.5f,-0.5f);
  }
  loaded=false;
}

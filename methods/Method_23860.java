@Override public void beginContour(){
  if (openContour) {
    PGraphics.showWarning("Already called beginContour()");
    return;
  }
  Path2D contourPath=auxPath;
  auxPath=workPath;
  workPath=contourPath;
  if (contourPath.getNumCommands() > 0) {
    breakShape=true;
  }
  openContour=true;
}

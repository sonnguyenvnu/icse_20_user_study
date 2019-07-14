@Override public void endContour(){
  if (!openContour) {
    PGraphics.showWarning("Need to call beginContour() first");
    return;
  }
  if (workPath.getNumCommands() > 0)   workPath.closePath();
  Path2D temp=workPath;
  workPath=auxPath;
  auxPath=temp;
  openContour=false;
}

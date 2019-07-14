private void recalculateMarkerPositions(){
  if (errorPoints != null && errorPoints.size() > 0) {
    Sketch sketch=editor.getSketch();
    SketchCode code=sketch.getCurrentCode();
    int currentTab=sketch.getCurrentCodeIndex();
    int totalLines=PApplet.max(1,code.getLineCount());
    int visibleLines=editor.getTextArea().getVisibleLines();
    totalLines=PApplet.max(totalLines,visibleLines);
    int topMargin=20;
    int bottomMargin=40;
    int height=getHeight() - topMargin - bottomMargin;
    for (    LineMarker m : errorPoints) {
      Problem problem=m.problem;
      if (problem.getTabIndex() != currentTab)       continue;
      float ratio=(problem.getLineNumber() + 1) / ((float)totalLines);
      float y=topMargin + ratio * height;
      m.y=(int)y;
    }
  }
}

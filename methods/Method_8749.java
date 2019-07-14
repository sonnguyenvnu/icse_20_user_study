private void initializeFadingEdges(){
  setVerticalFadingEdgeEnabled(true);
  setFadingEdgeLength((getBottom() - getTop() - mTextSize) / 2);
}

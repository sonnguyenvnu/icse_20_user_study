private void calculateDotPositions(int width,int height){
  int left=getPaddingLeft();
  int top=getPaddingTop();
  int right=width - getPaddingRight();
  int bottom=height - getPaddingBottom();
  int requiredWidth=getRequiredWidth();
  float startLeft=left + ((right - left - requiredWidth) / 2) + dotRadius;
  dotCenterX=new float[pageCount];
  for (int i=0; i < pageCount; i++) {
    dotCenterX[i]=startLeft + i * (dotDiameter + gap);
  }
  dotTopY=top;
  dotCenterY=top + dotRadius;
  dotBottomY=top + dotDiameter;
  setCurrentPageImmediate();
}

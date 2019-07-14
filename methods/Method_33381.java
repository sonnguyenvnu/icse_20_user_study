public void layoutLines(double x,double y,double w,double h,double controlHeight,double translateY){
  this.contentHeight=translateY;
  clip.setY(-contentHeight);
  clip.setHeight(controlHeight + contentHeight);
  focusedLine.resizeRelocate(x,controlHeight,w,focusedLine.prefHeight(-1));
  line.resizeRelocate(x,controlHeight,w,line.prefHeight(-1));
  promptContainer.resizeRelocate(x,y,w,h);
  scale.setPivotX(w / 2);
}

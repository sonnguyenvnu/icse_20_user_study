public void setLineColors(int color,int active,int error){
  lineColor=color;
  activeLineColor=active;
  errorLineColor=error;
  errorPaint.setColor(errorLineColor);
  invalidate();
}

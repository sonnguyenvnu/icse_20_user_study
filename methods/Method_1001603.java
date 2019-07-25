@Override public void moveto(double x1,double y1){
  data.add(new PostScriptCommandMoveTo(x1,y1));
  this.posX=x1;
  this.posY=y1;
  openMacro();
  ensureVisible(x1,y1);
}

@Override public void lineto(double x1,double y1){
  final PostScriptCommand cmd=new PostScriptCommandLineTo(x1 - posX,y1 - posY);
  macroInProgress.add(cmd);
  this.posX=x1;
  this.posY=y1;
  ensureVisible(x1,y1);
}

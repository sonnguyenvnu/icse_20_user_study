@Override public void curveto(double x1,double y1,double x2,double y2,double x3,double y3){
  final PostScriptCommandCurveTo cmd=new PostScriptCommandCurveTo(x1 - posX,y1 - posY,x2 - posX,y2 - posY,x3 - posX,y3 - posY);
  macroInProgress.add(cmd);
  this.posX=x3;
  this.posY=y3;
  ensureVisible(x1,y1);
  ensureVisible(x2,y2);
  ensureVisible(x3,y3);
}

void draw(){
  if (enabled)   g.setStrokeStyle("#000000");
 else   g.setStrokeStyle("lightgrey");
  g.setLineWidth(1.0);
  g.fillRect(0,0,CirSim.VERTICALPANELWIDTH,SCROLLHEIGHT);
  g.beginPath();
  g.moveTo(HMARGIN + SCROLLHEIGHT - 3,0);
  g.lineTo(HMARGIN,SCROLLHEIGHT / 2);
  g.lineTo(HMARGIN + SCROLLHEIGHT - 3,SCROLLHEIGHT);
  g.moveTo(CirSim.VERTICALPANELWIDTH - HMARGIN - SCROLLHEIGHT + 3,0);
  g.lineTo(CirSim.VERTICALPANELWIDTH - HMARGIN,SCROLLHEIGHT / 2);
  g.lineTo(CirSim.VERTICALPANELWIDTH - HMARGIN - SCROLLHEIGHT + 3,SCROLLHEIGHT);
  g.stroke();
  if (enabled)   g.setStrokeStyle("grey");
  g.beginPath();
  g.setLineWidth(5.0);
  g.moveTo(HMARGIN + SCROLLHEIGHT + BARMARGIN,SCROLLHEIGHT / 2);
  g.lineTo(CirSim.VERTICALPANELWIDTH - HMARGIN - SCROLLHEIGHT - BARMARGIN,SCROLLHEIGHT / 2);
  g.stroke();
  double p=HMARGIN + SCROLLHEIGHT + BARMARGIN + ((CirSim.VERTICALPANELWIDTH - 2 * (HMARGIN + SCROLLHEIGHT + BARMARGIN)) * ((double)(val - min))) / (max - min);
  if (enabled) {
    if (attachedElm != null && attachedElm.needsHighlight())     g.setStrokeStyle("cyan");
 else     g.setStrokeStyle("red");
    g.beginPath();
    g.moveTo(HMARGIN + SCROLLHEIGHT + BARMARGIN,SCROLLHEIGHT / 2);
    g.lineTo(p,SCROLLHEIGHT / 2);
    g.stroke();
    g.setStrokeStyle("#000000");
    g.setLineWidth(2.0);
    g.fillRect(p - 2,2,5,SCROLLHEIGHT - 4);
    g.strokeRect(p - 2,2,5,SCROLLHEIGHT - 4);
  }
}

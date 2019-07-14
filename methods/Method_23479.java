static protected void copyStyles(PShape src,PShape dest){
  dest.ellipseMode=src.ellipseMode;
  dest.rectMode=src.rectMode;
  if (src.stroke) {
    dest.stroke=true;
    dest.strokeColor=src.strokeColor;
    dest.strokeWeight=src.strokeWeight;
    dest.strokeCap=src.strokeCap;
    dest.strokeJoin=src.strokeJoin;
  }
 else {
    dest.stroke=false;
  }
  if (src.fill) {
    dest.fill=true;
    dest.fillColor=src.fillColor;
  }
 else {
    dest.fill=false;
  }
}

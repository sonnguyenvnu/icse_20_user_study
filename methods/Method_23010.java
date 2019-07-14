static public Shape createRoundRect(float x1,float y1,float x2,float y2,float tl,float tr,float br,float bl){
  GeneralPath path=new GeneralPath();
  if (tr != 0) {
    path.moveTo(x2 - tr,y1);
    path.quadTo(x2,y1,x2,y1 + tr);
  }
 else {
    path.moveTo(x2,y1);
  }
  if (br != 0) {
    path.lineTo(x2,y2 - br);
    path.quadTo(x2,y2,x2 - br,y2);
  }
 else {
    path.lineTo(x2,y2);
  }
  if (bl != 0) {
    path.lineTo(x1 + bl,y2);
    path.quadTo(x1,y2,x1,y2 - bl);
  }
 else {
    path.lineTo(x1,y2);
  }
  if (tl != 0) {
    path.lineTo(x1,y1 + tl);
    path.quadTo(x1,y1,x1 + tl,y1);
  }
 else {
    path.lineTo(x1,y1);
  }
  path.closePath();
  return path;
}

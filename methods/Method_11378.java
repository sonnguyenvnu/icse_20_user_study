/** 
 * ??????? ??????Path? ???????????
 * @param start ????
 * @param end   ????
 * @param path  ????????path?
 * @param outer ?????
 */
private void drawPartCircle(PointF start,PointF end,Path path,boolean outer){
  float c=0.551915024494f;
  PointF middle=new PointF(start.x + (end.x - start.x) / 2,start.y + (end.y - start.y) / 2);
  float r1=(float)Math.sqrt(Math.pow((middle.x - start.x),2) + Math.pow((middle.y - start.y),2));
  float gap1=r1 * c;
  if (start.x == end.x) {
    boolean topToBottom=end.y - start.y > 0 ? true : false;
    int flag;
    if (topToBottom) {
      flag=1;
    }
 else {
      flag=-1;
    }
    if (outer) {
      path.cubicTo(start.x + gap1 * flag,start.y,middle.x + r1 * flag,middle.y - gap1 * flag,middle.x + r1 * flag,middle.y);
      path.cubicTo(middle.x + r1 * flag,middle.y + gap1 * flag,end.x + gap1 * flag,end.y,end.x,end.y);
    }
 else {
      path.cubicTo(start.x - gap1 * flag,start.y,middle.x - r1 * flag,middle.y - gap1 * flag,middle.x - r1 * flag,middle.y);
      path.cubicTo(middle.x - r1 * flag,middle.y + gap1 * flag,end.x - gap1 * flag,end.y,end.x,end.y);
    }
  }
 else {
    boolean leftToRight=end.x - start.x > 0 ? true : false;
    int flag;
    if (leftToRight) {
      flag=1;
    }
 else {
      flag=-1;
    }
    if (outer) {
      path.cubicTo(start.x,start.y - gap1 * flag,middle.x - gap1 * flag,middle.y - r1 * flag,middle.x,middle.y - r1 * flag);
      path.cubicTo(middle.x + gap1 * flag,middle.y - r1 * flag,end.x,end.y - gap1 * flag,end.x,end.y);
    }
 else {
      path.cubicTo(start.x,start.y + gap1 * flag,middle.x - gap1 * flag,middle.y + r1 * flag,middle.x,middle.y + r1 * flag);
      path.cubicTo(middle.x + gap1 * flag,middle.y + r1 * flag,end.x,end.y + gap1 * flag,end.x,end.y);
    }
  }
}

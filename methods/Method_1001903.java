/** 
 * Delegates to the enclosed <code>GeneralPath</code>.
 */
public void append(PathIterator pi,boolean connect){
  final double[] vals=new double[6];
  while (!pi.isDone()) {
    Arrays.fill(vals,0);
    int type=pi.currentSegment(vals);
    pi.next();
    if (connect && numVals != 0) {
      if (type == PathIterator.SEG_MOVETO) {
        final double x=vals[0];
        final double y=vals[1];
        if (x != cx || y != cy) {
          type=PathIterator.SEG_LINETO;
        }
 else {
          if (pi.isDone()) {
            break;
          }
          type=pi.currentSegment(vals);
          pi.next();
        }
      }
      connect=false;
    }
switch (type) {
case PathIterator.SEG_CLOSE:
      closePath();
    break;
case PathIterator.SEG_MOVETO:
  moveTo(vals[0],vals[1]);
break;
case PathIterator.SEG_LINETO:
lineTo(vals[0],vals[1]);
break;
case PathIterator.SEG_QUADTO:
quadTo(vals[0],vals[1],vals[2],vals[3]);
break;
case PathIterator.SEG_CUBICTO:
curveTo(vals[0],vals[1],vals[2],vals[3],vals[4],vals[5]);
break;
}
}
}

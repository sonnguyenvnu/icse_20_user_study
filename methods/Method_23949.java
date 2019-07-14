private static void pathTo(PathIterator pi,LineStroker lsink){
  float coords[]=new float[6];
  while (!pi.isDone()) {
    int color;
switch (pi.currentSegment(coords)) {
case SEG_MOVETO:
      color=((int)coords[2] << 24) | ((int)coords[3] << 16) | ((int)coords[4] << 8) | (int)coords[5];
    lsink.moveTo(FloatToS15_16(coords[0]),FloatToS15_16(coords[1]),color);
  break;
case SEG_LINETO:
color=((int)coords[2] << 24) | ((int)coords[3] << 16) | ((int)coords[4] << 8) | (int)coords[5];
lsink.lineJoin();
lsink.lineTo(FloatToS15_16(coords[0]),FloatToS15_16(coords[1]),color);
break;
case SEG_CLOSE:
lsink.lineJoin();
lsink.close();
break;
default :
throw new InternalError("unknown flattened segment type");
}
pi.next();
}
lsink.end();
}

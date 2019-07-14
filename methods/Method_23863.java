private void drawShape(Shape s){
  context.beginPath();
  PathIterator pi=s.getPathIterator(null);
  while (!pi.isDone()) {
    int pitype=pi.currentSegment(pathCoordsBuffer);
switch (pitype) {
case PathIterator.SEG_MOVETO:
      context.moveTo(pathCoordsBuffer[0],pathCoordsBuffer[1]);
    break;
case PathIterator.SEG_LINETO:
  context.lineTo(pathCoordsBuffer[0],pathCoordsBuffer[1]);
break;
case PathIterator.SEG_QUADTO:
context.quadraticCurveTo(pathCoordsBuffer[0],pathCoordsBuffer[1],pathCoordsBuffer[2],pathCoordsBuffer[3]);
break;
case PathIterator.SEG_CUBICTO:
context.bezierCurveTo(pathCoordsBuffer[0],pathCoordsBuffer[1],pathCoordsBuffer[2],pathCoordsBuffer[3],pathCoordsBuffer[4],pathCoordsBuffer[5]);
break;
case PathIterator.SEG_CLOSE:
context.closePath();
break;
default :
showWarning("Unknown segment type " + pitype);
}
pi.next();
}
if (fill) context.fill();
if (stroke) context.stroke();
}

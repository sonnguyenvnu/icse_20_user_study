@Override protected void arcImpl(float x,float y,float w,float h,float start,float stop,int mode){
  beforeContextDraw();
  if (drawingThinLines()) {
    x+=0.5f;
    y+=0.5f;
  }
  start=-start;
  stop=-stop;
  float sweep=stop - start;
  ArcType fillMode=ArcType.ROUND;
  ArcType strokeMode=ArcType.OPEN;
  if (mode == OPEN) {
    fillMode=ArcType.OPEN;
  }
 else   if (mode == PIE) {
    strokeMode=ArcType.ROUND;
  }
 else   if (mode == CHORD) {
    fillMode=ArcType.CHORD;
    strokeMode=ArcType.CHORD;
  }
  if (fill) {
    context.fillArc(x,y,w,h,PApplet.degrees(start),PApplet.degrees(sweep),fillMode);
  }
  if (stroke) {
    context.strokeArc(x,y,w,h,PApplet.degrees(start),PApplet.degrees(sweep),strokeMode);
  }
}

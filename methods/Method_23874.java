@Override protected void rectImpl(float x1,float y1,float x2,float y2){
  beforeContextDraw();
  if (drawingThinLines()) {
    x1+=0.5f;
    x2+=0.5f;
    y1+=0.5f;
    y2+=0.5f;
  }
  if (fill)   context.fillRect(x1,y1,x2 - x1,y2 - y1);
  if (stroke)   context.strokeRect(x1,y1,x2 - x1,y2 - y1);
}

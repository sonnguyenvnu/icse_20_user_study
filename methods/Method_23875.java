@Override protected void ellipseImpl(float x,float y,float w,float h){
  beforeContextDraw();
  if (drawingThinLines()) {
    x+=0.5f;
    y+=0.5f;
  }
  if (fill)   context.fillOval(x,y,w,h);
  if (stroke)   context.strokeOval(x,y,w,h);
}

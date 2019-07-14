@Override protected PShape createShapePrimitive(int kind,float... p){
  PShape shape=new PShapeOpenGL(this,kind,p);
  if (is3D()) {
    shape.set3D(true);
  }
  return shape;
}

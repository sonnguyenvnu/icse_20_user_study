public static MultiShapeBuilder<Shape> getGeometryCollectionBuilder(){
  return getShapeFactory().multiShape(Shape.class);
}

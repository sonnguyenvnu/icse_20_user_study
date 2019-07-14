public int size(Shape shape){
switch (getType(shape)) {
case LINE:
case POLYGON:
case MULTIPOINT:
case MULTILINESTRING:
case MULTIPOLYGON:
    return ((JtsGeometry)shape).getGeom().getCoordinates().length;
case POINT:
  return 1;
case CIRCLE:
return 1;
case BOX:
return 2;
case GEOMETRYCOLLECTION:
return ((ShapeCollection<?>)shape).getShapes().stream().map(s -> (Shape)s).mapToInt(s -> size(s)).sum();
default :
throw new IllegalStateException("size() not supported for type: " + getType(shape));
}
}

public Geoshape geoshape(org.locationtech.jts.geom.Geometry geometry){
  return new Geoshape(context.getShapeFactory().makeShapeFromGeometry(geometry));
}

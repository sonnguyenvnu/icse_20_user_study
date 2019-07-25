/** 
 * Create a Multipolygon from a set of coordinates. Each primary array contains a polygon which in turn contains an array of linestrings. These line Strings are represented as an array of coordinates. The first linestring will be the shell of the polygon the others define holes within the polygon.
 * @param factory {@link GeometryFactory} to use
 * @param polygons definition of polygons
 * @return a new Multipolygon
 */
protected static MultiPolygon multipolygon(GeometryFactory factory,Coordinate[][][] polygons){
  Polygon[] polygonSet=new Polygon[polygons.length];
  for (int i=0; i < polygonSet.length; i++) {
    polygonSet[i]=polygon(factory,polygons[i]);
  }
  return factory.createMultiPolygon(polygonSet);
}

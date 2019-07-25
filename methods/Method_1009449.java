public ElementGeometry create(Way way){
  List<LatLon> polyline=wayGeometrySource.getNodePositions(way.getId());
  if (polyline.isEmpty())   return null;
  eliminateDuplicates(polyline);
  List<List<LatLon>> polylines=new ArrayList<>(1);
  polylines.add(polyline);
  ElementGeometry result;
  if (OsmAreas.isArea(way)) {
    if (SphericalEarthMath.isRingDefinedClockwise(polyline)) {
      Collections.reverse(polyline);
    }
    result=new ElementGeometry(null,polylines);
  }
 else {
    result=new ElementGeometry(polylines,null);
  }
  if (result.center == null)   return null;
  return result;
}

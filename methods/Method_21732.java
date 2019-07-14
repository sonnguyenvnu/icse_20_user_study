public static String toGeoJson(String wkt){
  wkt=wkt.toLowerCase();
  int startOfCoordinates=wkt.indexOf("(");
  if (startOfCoordinates == -1)   throw new IllegalArgumentException("not valid wkt");
  String wktType=wkt.substring(0,startOfCoordinates).trim();
  wkt=wkt.substring(startOfCoordinates);
  String type="";
  String coordinates="";
switch (wktType) {
case ("point"):
    type="Point";
  coordinates=pointCoordinatesFromWkt(wkt);
break;
case ("polygon"):
type="Polygon";
coordinates=polygonCoordinatesFromWkt(wkt);
break;
case ("linestring"):
type="LineString";
coordinates=lineStringCoordinatesFromWkt(wkt);
break;
case ("multipolygon"):
type="MultiPolygon";
coordinates=multiPolygonCoordinatesFromWkt(wkt);
break;
case ("multipoint"):
type="MultiPoint";
coordinates=multiPointCoordinatesFromWkt(wkt);
break;
case ("multilinestring"):
type="MultiLineString";
coordinates=multiLineStringCoordinatesFromWkt(wkt);
break;
default :
throw new IllegalArgumentException("not supported wkt type");
}
return buildGeoJson(type,coordinates);
}

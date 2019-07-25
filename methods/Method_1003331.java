private static void parse(JSONValue v,GeometryUtils.Target target){
  if (v instanceof JSONNull) {
    target.startPoint();
    target.addCoordinate(Double.NaN,Double.NaN,Double.NaN,Double.NaN,0,1);
    target.endObject(POINT);
  }
 else   if (v instanceof JSONObject) {
    JSONObject o=(JSONObject)v;
    JSONValue t=o.getFirst("type");
    if (!(t instanceof JSONString)) {
      throw new IllegalArgumentException();
    }
switch (((JSONString)t).getString()) {
case "Point":
      parse(o,target,POINT);
    break;
case "LineString":
  parse(o,target,LINE_STRING);
break;
case "Polygon":
parse(o,target,POLYGON);
break;
case "MultiPoint":
parse(o,target,MULTI_POINT);
break;
case "MultiLineString":
parse(o,target,MULTI_LINE_STRING);
break;
case "MultiPolygon":
parse(o,target,MULTI_POLYGON);
break;
case "GeometryCollection":
parseGeometryCollection(o,target);
break;
default :
throw new IllegalArgumentException();
}
}
 else {
throw new IllegalArgumentException();
}
}

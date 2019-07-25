/** 
 * Constructor for a Geometry instance from an Open Geospatial Consortium (OGC) Well-Known Text (WKT) representation. Spatial Reference Identifier is defaulted to 0.
 * @param wkt Well-Known Text (WKT) provided by the user.
 * @return Geometry Geometry instance created from WKT
 * @throws SQLServerException if an exception occurs
 */
public static Geometry parse(String wkt) throws SQLServerException {
  return new Geometry(wkt,0);
}

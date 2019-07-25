/** 
 * Constructor for a Geography instance from an Open Geospatial Consortium (OGC) Well-Known Text (WKT) representation. Spatial Reference Identifier is defaulted to 4326.
 * @param wkt Well-Known Text (WKT) provided by the user.
 * @return Geography Geography instance created from WKT
 * @throws SQLServerException if an exception occurs
 */
public static Geography parse(String wkt) throws SQLServerException {
  return new Geography(wkt,4326);
}

/** 
 * Constructor for a Geometry instance from an internal SQL Server format for spatial data.
 * @param wkb Well-Known Binary (WKB) provided by the user.
 * @return Geometry Geometry instance created from WKB
 * @throws SQLServerException if an exception occurs
 */
public static Geometry deserialize(byte[] wkb) throws SQLServerException {
  return new Geometry(wkb);
}

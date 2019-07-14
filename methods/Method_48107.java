/** 
 * Constructs a polygon from list of coordinates
 * @param coordinates Coordinate (lon,lat) pairs
 * @return
 */
public static Geoshape polygon(List<double[]> coordinates){
  return HELPER.polygon(coordinates);
}

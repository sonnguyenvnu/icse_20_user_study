/** 
 * Calculate all neighbors of a given geohash cell.
 * @param geohash Geohash of the defined cell
 * @return geohashes of all neighbor cells
 */
public static Collection<? extends CharSequence> neighbors(String geohash){
  return addNeighbors(geohash,geohash.length(),new ArrayList<CharSequence>(8));
}

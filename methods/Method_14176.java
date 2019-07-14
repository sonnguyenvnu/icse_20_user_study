/** 
 * Registers a new distance under a name.
 */
public static void put(String name,SimilarityDistance distance){
  _distances.put(name,distance);
  _distanceNames.add(name);
}

/** 
 * For each data point, find the corresponding cell, then increment the count. This is the most inefficient portion of this example. <p> room for improvement: replace with some kind of geospatial indexing mechanism
 * @param iGeoPoint
 * @param heatmap
 * @return
 */
private int increment(IGeoPoint iGeoPoint,Map<BoundingBox,Integer> heatmap){
  Iterator<Map.Entry<BoundingBox,Integer>> iterator=heatmap.entrySet().iterator();
  while (iterator.hasNext()) {
    Map.Entry<BoundingBox,Integer> next=iterator.next();
    if (next.getKey().contains(iGeoPoint)) {
      int newval=next.getValue() + 1;
      heatmap.put(next.getKey(),newval);
      return newval;
    }
  }
  return 0;
}

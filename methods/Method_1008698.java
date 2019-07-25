/** 
 * The geohash of the geo point to create the range distance facets from. Deprecated - please use points(GeoPoint... points) instead.
 */
@Deprecated public GeoDistanceSortBuilder geohashes(String... geohashes){
  for (  String geohash : geohashes) {
    this.points.add(GeoPoint.fromGeohash(geohash));
  }
  return this;
}

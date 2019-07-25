/** 
 * Add an embedded shape markers
 * @param googleShapeMarkers
 */
public void add(OsmdroidShapeMarkers googleShapeMarkers){
  shapeMarkersMap.putAll(googleShapeMarkers.shapeMarkersMap);
}

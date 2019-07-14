/** 
 * @param newMarker
 * @return
 */
public boolean isCompatible(ReadMarker newMarker){
  if (newMarker.hasIdentifier()) {
    return hasIdentifier() && identifier.equals(newMarker.identifier);
  }
  return !newMarker.hasStartTime();
}

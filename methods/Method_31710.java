/** 
 * Retrieves this location's parent within this list, if any.
 * @param location       The location to check.
 * @param finalLocations The list to search.
 * @return The parent location. {@code null} if none.
 */
private Location getParentLocationIfExists(Location location,List<Location> finalLocations){
  for (  Location finalLocation : finalLocations) {
    if (finalLocation.isParentOf(location)) {
      return finalLocation;
    }
  }
  return null;
}

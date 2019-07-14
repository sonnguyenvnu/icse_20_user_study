private void processLocations(List<Location> rawLocations){
  List<Location> sortedLocations=new ArrayList<>(rawLocations);
  Collections.sort(sortedLocations);
  for (  Location normalizedLocation : sortedLocations) {
    if (locations.contains(normalizedLocation)) {
      LOG.warn("Discarding duplicate location '" + normalizedLocation + "'");
      continue;
    }
    Location parentLocation=getParentLocationIfExists(normalizedLocation,locations);
    if (parentLocation != null) {
      LOG.warn("Discarding location '" + normalizedLocation + "' as it is a sublocation of '" + parentLocation + "'");
      continue;
    }
    locations.add(normalizedLocation);
  }
}

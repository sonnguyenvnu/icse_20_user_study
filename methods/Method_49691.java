/** 
 * Note: The search for currentLocation and mapLocation locality is done locally only.
 * @param currentLocation If not null, the locality of the {@code currentLocation} will appearfirst in the list
 * @param mapLocation     If not null, the locality of the {@code mapLocation} will appear nextin the list
 * @return the list of localities to show in the search suggestions
 */
public static List<String> getAllLocalitiesForSearchDisplay(OpenLocationCode currentLocation,OpenLocationCode mapLocation){
  List<String> allLocalities=new ArrayList<>(LOCALITIES_WITHOUT_CODE_ALPHABETICAL);
  if (mapLocation != null) {
    String mapLocationLocality;
    try {
      mapLocationLocality=getNearestLocality(mapLocation);
      int index=allLocalities.indexOf(mapLocationLocality);
      allLocalities.remove(index);
      allLocalities.add(0,mapLocationLocality);
    }
 catch (    NoLocalityException e) {
      Log.d(TAG,"map not centered on CV");
    }
  }
  if (currentLocation != null) {
    String currentLocationLocality;
    try {
      currentLocationLocality=getNearestLocality(currentLocation);
      int index=allLocalities.indexOf(currentLocationLocality);
      allLocalities.remove(index);
      allLocalities.add(0,currentLocationLocality);
    }
 catch (    NoLocalityException e) {
      Log.d(TAG,"current location not in CV");
    }
  }
  return allLocalities;
}

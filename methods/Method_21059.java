public static boolean isDiscoverPlacesPath(final @NonNull String path){
  return DISCOVER_PLACES_PATTERN.matcher(path).matches();
}

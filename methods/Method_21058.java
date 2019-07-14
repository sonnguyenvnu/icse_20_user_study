public static boolean isDiscoverCategoriesPath(final @NonNull String path){
  return DISCOVER_CATEGORIES_PATTERN.matcher(path).matches();
}

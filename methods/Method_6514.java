public BitmapDrawable getAnyImageFromMemory(String key){
  BitmapDrawable drawable=memCache.get(key);
  if (drawable == null) {
    ArrayList<String> filters=memCache.getFilterKeys(key);
    if (filters != null && !filters.isEmpty()) {
      return memCache.get(key + "@" + filters.get(0));
    }
  }
  return drawable;
}

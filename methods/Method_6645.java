public ArrayList<String> getFilterKeys(String key){
  ArrayList<String> arr=mapFilters.get(key);
  if (arr != null) {
    return new ArrayList<>(arr);
  }
  return null;
}

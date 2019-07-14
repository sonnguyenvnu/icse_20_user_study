private void updateFieldNamesToAlias(Map<String,Object> sourceAsMap,Map<String,String> fieldNameToAlias){
  for (  Map.Entry<String,String> fieldToAlias : fieldNameToAlias.entrySet()) {
    String fieldName=fieldToAlias.getKey();
    Object value=null;
    Map<String,Object> deleteFrom=null;
    if (fieldName.contains(".")) {
      String[] split=fieldName.split("\\.");
      String[] path=Arrays.copyOf(split,split.length - 1);
      Object placeInMap=Util.searchPathInMap(sourceAsMap,path);
      if (placeInMap != null) {
        if (!Map.class.isAssignableFrom(placeInMap.getClass())) {
          continue;
        }
      }
      deleteFrom=(Map<String,Object>)placeInMap;
      value=deleteFrom.get(split[split.length - 1]);
    }
 else     if (sourceAsMap.containsKey(fieldName)) {
      value=sourceAsMap.get(fieldName);
      deleteFrom=sourceAsMap;
    }
    if (value != null) {
      sourceAsMap.put(fieldToAlias.getValue(),value);
      deleteFrom.remove(fieldName);
    }
  }
  Util.clearEmptyPaths(sourceAsMap);
}

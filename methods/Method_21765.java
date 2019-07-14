public static Object searchPathInMap(Map<String,Object> fieldsMap,String[] path){
  Map<String,Object> currentObject=fieldsMap;
  for (int i=0; i < path.length - 1; i++) {
    Object valueFromCurrentMap=currentObject.get(path[i]);
    if (valueFromCurrentMap == null)     return null;
    if (!Map.class.isAssignableFrom(valueFromCurrentMap.getClass()))     return null;
    currentObject=(Map<String,Object>)valueFromCurrentMap;
  }
  return currentObject.get(path[path.length - 1]);
}

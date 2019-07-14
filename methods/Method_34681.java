@Override protected boolean isFallbackUserDefined(){
  Boolean containsFromMap=commandContainsFallback.get(commandKey);
  if (containsFromMap != null) {
    return containsFromMap;
  }
 else {
    Boolean toInsertIntoMap;
    try {
      getClass().getDeclaredMethod("getFallback");
      toInsertIntoMap=true;
    }
 catch (    NoSuchMethodException nsme) {
      toInsertIntoMap=false;
    }
    commandContainsFallback.put(commandKey,toInsertIntoMap);
    return toInsertIntoMap;
  }
}

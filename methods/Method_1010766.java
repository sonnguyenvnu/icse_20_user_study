public synchronized Boolean put(String lang,ConceptEditorHint hint,boolean value){
  if (!mySettings.containsKey(lang)) {
    mySettings.put(lang,Collections.synchronizedMap(new LinkedHashMap<>()));
  }
  return mySettings.get(lang).put(hint,value);
}

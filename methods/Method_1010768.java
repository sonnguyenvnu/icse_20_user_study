public synchronized Boolean remove(String lang,ConceptEditorHint hint){
  if (!mySettings.containsKey(lang)) {
    return null;
  }
 else {
    Boolean result=mySettings.get(lang).remove(hint);
    if (mySettings.get(lang).isEmpty()) {
      mySettings.remove(lang);
    }
    return result;
  }
}

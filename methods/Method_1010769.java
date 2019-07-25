public Boolean get(String lang,ConceptEditorHint hint){
  if (!mySettings.containsKey(lang)) {
    return null;
  }
  return mySettings.get(lang).get(hint);
}

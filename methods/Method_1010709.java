@Override public boolean test(@Nullable SAbstractConcept concept){
  return concept == null || myModel == null || myUsedLanguages.contains(concept.getLanguage());
}

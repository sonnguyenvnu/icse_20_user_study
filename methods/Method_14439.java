protected boolean saveNeeded(){
  boolean projectSaveNeeded=_projectsMetadata.entrySet().stream().anyMatch(e -> e.getValue() != null && e.getValue().isDirty());
  return projectSaveNeeded || _preferenceStore.isDirty();
}

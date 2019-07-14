@NotNull @Override public String toString(){
  return "(analyzer:" + "[" + allBindings.size() + " bindings] " + "[" + references.size() + " refs] " + "[" + loadedFiles.size() + " files] " + ")";
}

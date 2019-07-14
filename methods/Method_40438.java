private void addSemanticStyle(Def def,StyleRun.Type type){
  String path=def.getFile();
  if (path != null) {
    addFileStyle(path,new StyleRun(type,def.getStart(),def.getLength()));
  }
}

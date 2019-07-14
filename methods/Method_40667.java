@NotNull public String getFirstFile(){
  Type bt=type;
  if (bt instanceof ModuleType) {
    String file=bt.asModuleType().file;
    return file != null ? file : "<built-in module>";
  }
  String file=this.file;
  if (file != null) {
    return file;
  }
  return "<built-in binding>";
}

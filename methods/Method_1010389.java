protected String expand(String path,@Nullable IFile anchorFile){
  new PathAssert(path).osIndependentPath();
  if (!MacrosFactory.containsMacro(path)) {
    return path;
  }
  int macroEnd=path.indexOf('}');
  String macro=path.substring(2,macroEnd);
  String macroValue=myComponent.getValue(macro);
  if (macroValue == null) {
    myComponent.report("Please define path variable in path variables section of settings",macro);
    return path;
  }
  String expanded=macroValue + path.substring(macroEnd + 1);
  return FileUtil.resolveParentDirs(expanded);
}

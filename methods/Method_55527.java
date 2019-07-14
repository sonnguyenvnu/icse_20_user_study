@Nullable private static Path load(String name,String property,String paths){
  Path resource=Library.findFile(paths,name);
  if (resource == null) {
    apiLog(String.format("\t%s not found in %s=%s",name,property,paths));
    return null;
  }
  apiLog(String.format("\tLoaded from %s: %s",property,resource));
  return resource;
}

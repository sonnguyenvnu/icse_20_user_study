private Set<String> getAllParentPathsStr(String rowPathStr){
  Set<String> parents=new HashSet<>();
  parents.add(rowPathStr);
  if (rowPathStr.contains("/")) {
    String[] pathElements=rowPathStr.split("/");
    String path="";
    for (    String pathElement : pathElements) {
      path=path + pathElement + "/";
      parents.add(path);
    }
  }
  return parents;
}

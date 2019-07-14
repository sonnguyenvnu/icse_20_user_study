/** 
 * Attempts to get the module name of the given package. We can do this because the package name of an R class is the module. Generally only one R class is used and we can just use that module name, but it is possible to have multiple R classes. In that case we compare the package names to find what is the most similar. <p> We need to get the module name to know the path of the BR class for data binding.
 */
private String getModuleNameViaResources(String packageName){
  List<ClassName> rClasses=resourceProcessor.getRClassNames();
  if (rClasses.isEmpty()) {
    return packageName;
  }
  if (rClasses.size() == 1) {
    return rClasses.get(0).packageName();
  }
  String[] packageNames=packageName.split("\\.");
  ClassName bestMatch=null;
  int bestNumMatches=-1;
  for (  ClassName rClass : rClasses) {
    String[] rModuleNames=rClass.packageName().split("\\.");
    int numNameMatches=0;
    for (int i=0; i < Math.min(packageNames.length,rModuleNames.length); i++) {
      if (packageNames[i].equals(rModuleNames[i])) {
        numNameMatches++;
      }
 else {
        break;
      }
    }
    if (numNameMatches > bestNumMatches) {
      bestMatch=rClass;
    }
  }
  return bestMatch.packageName();
}

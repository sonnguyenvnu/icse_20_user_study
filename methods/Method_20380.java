/** 
 * Attempts to get the android module that is currently being processed.. We can do this because the package name of an R class is the module name. So, we take any element in the module, <p> We need to get the module name to know the path of the BR class for data binding.
 */
private String getModuleNameViaGuessing(String packageName){
  String[] packageNameParts=packageName.split("\\.");
  String moduleName="";
  for (int i=0; i < packageNameParts.length; i++) {
    moduleName+=packageNameParts[i];
    Element rClass=Utils.getElementByName(moduleName + ".R",elements,types);
    if (rClass != null) {
      return moduleName;
    }
 else {
      moduleName+=".";
    }
  }
  return null;
}

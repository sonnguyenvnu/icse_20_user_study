/** 
 * Finds mapping for given action class. Returns <code>null</code> if no mapping is found. If there is more then one matching root package, the closest one will be returned.
 */
public String findPackagePathForActionPackage(final String actionPackage){
  if (packages == null) {
    return null;
  }
  if (packagePaths == null) {
    packagePaths=new HashMap<>();
  }
  String packagePath=packagePaths.get(actionPackage);
  if (packagePath != null) {
    return packagePath;
  }
  int ndx=-1;
  int delta=Integer.MAX_VALUE;
  for (int i=0; i < packages.length; i++) {
    String rootPackage=packages[i];
    if (rootPackage.equals(actionPackage)) {
      ndx=i;
      delta=0;
      break;
    }
    rootPackage+='.';
    if (actionPackage.startsWith(rootPackage)) {
      int distanceFromTheRoot=actionPackage.length() - rootPackage.length();
      if (distanceFromTheRoot < delta) {
        ndx=i;
        delta=distanceFromTheRoot;
      }
    }
  }
  if (ndx == -1) {
    return null;
  }
  String packageActionPath=delta == 0 ? StringPool.EMPTY : StringUtil.substring(actionPackage,-delta - 1,0);
  packageActionPath=packageActionPath.replace('.','/');
  String result=mappings[ndx] + packageActionPath;
  packagePaths.put(actionPackage,result);
  return result;
}

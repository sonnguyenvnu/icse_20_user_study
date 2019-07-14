/** 
 * Reads action path for package. If annotation is not set on package-level, class package will be used for package action path part.
 */
protected String[] readPackageActionPath(final Class actionClass){
  Package actionPackage=actionClass.getPackage();
  final String actionPackageName=actionPackage.getName();
  String packageActionPathFromAnnotation;
  mainloop:   while (true) {
    MadvocAction madvocActionAnnotation=actionPackage.getAnnotation(MadvocAction.class);
    packageActionPathFromAnnotation=madvocActionAnnotation != null ? madvocActionAnnotation.value().trim() : null;
    if (StringUtil.isEmpty(packageActionPathFromAnnotation)) {
      packageActionPathFromAnnotation=null;
    }
    if (packageActionPathFromAnnotation == null) {
      String newPackage=actionPackage.getName();
      actionPackage=null;
      while (actionPackage == null) {
        final int ndx=newPackage.lastIndexOf('.');
        if (ndx == -1) {
          break mainloop;
        }
        newPackage=newPackage.substring(0,ndx);
        actionPackage=Packages.of(actionClass.getClassLoader(),newPackage);
      }
    }
 else {
      rootPackages.addRootPackage(actionPackage.getName(),packageActionPathFromAnnotation);
      break;
    }
  }
  String packagePath=rootPackages.findPackagePathForActionPackage(actionPackageName);
  if (packagePath == null) {
    return ArraysUtil.array(null,null);
  }
  return ArraysUtil.array(StringUtil.stripChar(packagePath,'/'),StringUtil.surround(packagePath,StringPool.SLASH));
}

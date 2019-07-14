/** 
 * Returns the deepest PackageStats that contains the named resource. If the second parameter is set, creates the missing PackageStats along the way.
 * @param qname            The qualified name of the resource
 * @param createIfNotFound If set to true, the hierarch is created if missing
 * @return The deepest package that contains this resource. Can only return null if createIfNotFound is unset
 */
private PackageStats getSubPackage(JavaTypeQualifiedName qname,boolean createIfNotFound){
  if (qname.getPackageList().isEmpty()) {
    return this;
  }
  List<String> packagePath=qname.getPackageList();
  PackageStats next=this;
  for (Iterator<String> it=packagePath.iterator(); it.hasNext() && next != null; ) {
    String currentPackage=it.next();
    if (createIfNotFound && next.subPackages.get(currentPackage) == null) {
      next.subPackages.put(currentPackage,new PackageStats());
    }
    next=next.subPackages.get(currentPackage);
  }
  return next;
}

/** 
 * Builds a JavaPoet ClassName from the string value of an R class. This is memoized since there should be very few different R classes used.
 */
private ClassName getClassName(String rClass,String resourceType){
  ClassName className=rClassNameMap.get(rClass);
  if (className == null) {
    Element rClassElement=getElementByName(rClass,elementUtils,typeUtils);
    String rClassPackageName=elementUtils.getPackageOf(rClassElement).getQualifiedName().toString();
    className=ClassName.get(rClassPackageName,"R",resourceType);
    rClassNameMap.put(rClass,className);
  }
  return className;
}

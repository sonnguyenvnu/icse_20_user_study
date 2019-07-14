private static boolean classIsInExactPackage(String className,String packageName){
  return packageName.equals(className.substring(0,className.lastIndexOf('/') + 1));
}

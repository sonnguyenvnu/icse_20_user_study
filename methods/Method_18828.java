public static ClassName buildClassName(String typeName){
  final int lastIndexOfDotForPackage=typeName.lastIndexOf('.');
  final int lastIndexOfDotForClass=typeName.lastIndexOf('.') + 1;
  final String packageName=typeName.substring(0,lastIndexOfDotForPackage);
  final String className=typeName.substring(lastIndexOfDotForClass);
  return ClassName.get(packageName,className);
}

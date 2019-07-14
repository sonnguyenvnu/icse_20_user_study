private static String getTypeNameString(ClassName className){
  ClassName enclosingClass=className.enclosingClassName();
  if (enclosingClass == null) {
    return className.toString();
  }
  return enclosingClass.toString() + "$" + className.simpleName();
}

private static String rootPackage(Import anImport){
  String type=anImport.getType();
  if (type.startsWith("com.android.")) {
    return "com.android";
  }
  int index=type.indexOf('.');
  if (index == -1) {
    return "";
  }
 else {
    return type.substring(0,index);
  }
}

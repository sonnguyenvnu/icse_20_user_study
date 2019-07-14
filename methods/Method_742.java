public static boolean isAndroid(String vmName){
  if (vmName == null) {
    return false;
  }
  String lowerVMName=vmName.toLowerCase();
  return lowerVMName.contains("dalvik") || lowerVMName.contains("lemur");
}

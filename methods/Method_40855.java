public static String unifyPath(File file){
  try {
    return file.getCanonicalPath();
  }
 catch (  Exception e) {
    abort("Failed to get canonical path");
    return "";
  }
}

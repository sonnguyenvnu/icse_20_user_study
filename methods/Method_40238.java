public static String unifyPath(File file){
  try {
    return file.getCanonicalPath();
  }
 catch (  Exception e) {
    die("Failed to get canonical path");
    return "";
  }
}

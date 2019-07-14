/** 
 * Attempts to determine the relative path to the file. If relative path cannot be found, the original path is returnedi, ie - the current path for the supplied file.
 * @param fileName well, the file with its original path.
 * @return the relative path to the file
 */
private String getRelativePath(String fileName){
  String relativePath;
  if (pwd == null) {
    try {
      this.pwd=new File(".").getCanonicalPath();
    }
 catch (    IOException ioErr) {
      this.pwd="";
    }
  }
  if (fileName.indexOf(this.pwd) == 0) {
    relativePath="." + fileName.substring(this.pwd.length());
    if (relativePath.startsWith("." + File.separator + "." + File.separator)) {
      relativePath=relativePath.substring(2);
    }
  }
 else {
    relativePath=fileName;
  }
  return relativePath;
}

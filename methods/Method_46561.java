/** 
 * Locates a file on the file system or on the classpath.   <p> Search order: <ol> <li>current working directory (for relative filePath) or any directory (for absolute filePath)</li> <li>class filePath</li> </ol>
 * @param file the relative filePath of the file to locate
 * @return A URL to the file or null if not found
 */
public static URL locateFile(String file){
  File fp;
  URL result=null;
  try {
    fp=new File(file);
    if (fp.exists()) {
      result=fp.toURI().toURL();
    }
    if (result == null) {
      result=locateOnClassPath(file);
    }
  }
 catch (  Exception e) {
  }
  return result;
}

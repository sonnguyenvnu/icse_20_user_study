/** 
 * Converts this file into a resource name on the classpath.
 * @param classPathRootOnDisk The location of the classpath root on disk, with a trailing slash.
 * @param file                The file.
 * @return The resource name on the classpath.
 */
private String toResourceNameOnClasspath(String classPathRootOnDisk,File file){
  String fileName=file.getAbsolutePath().replace("\\","/");
  return fileName.substring(classPathRootOnDisk.length());
}

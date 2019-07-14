/** 
 * Converts this fileName into a file, adjusting relative paths if necessary to make them relative to the pom.
 * @param workDir  The working directory to use.
 * @param fileName The name of the file, relative or absolute.
 * @return The resulting file.
 */
private File toFile(File workDir,String fileName){
  File file=new File(fileName);
  if (file.isAbsolute()) {
    return file;
  }
  return new File(workDir,fileName);
}

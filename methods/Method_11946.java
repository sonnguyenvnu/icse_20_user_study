/** 
 * Returns a new fresh file with a random name under the temporary folder.
 */
public File newFile() throws IOException {
  return File.createTempFile(TMP_PREFIX,null,getRoot());
}

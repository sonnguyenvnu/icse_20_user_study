/** 
 * Returns a new fresh folder with the given path under the temporary folder.
 */
public File newFolder(String path) throws IOException {
  return newFolder(new String[]{path});
}

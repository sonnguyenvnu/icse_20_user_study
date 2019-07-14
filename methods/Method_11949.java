/** 
 * Returns a new fresh folder with a random name under the temporary folder.
 */
public File newFolder() throws IOException {
  return createTemporaryFolderIn(getRoot());
}

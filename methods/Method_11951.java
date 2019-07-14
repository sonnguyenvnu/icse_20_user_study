/** 
 * @return the location of this temporary folder.
 */
public File getRoot(){
  if (folder == null) {
    throw new IllegalStateException("the temporary folder has not yet been created");
  }
  return folder;
}

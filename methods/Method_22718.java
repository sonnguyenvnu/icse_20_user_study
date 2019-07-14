/** 
 * Create the code folder if it does not exist already. As a convenience, it also returns the code folder, since it's likely about to be used.
 */
public File prepareCodeFolder(){
  if (!codeFolder.exists()) {
    codeFolder.mkdirs();
  }
  return codeFolder;
}

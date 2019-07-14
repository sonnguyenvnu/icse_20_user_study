/** 
 * Override this to control the order of the first set of example folders and how they appear in the examples window.
 */
public File[] getExampleCategoryFolders(){
  return examplesFolder.listFiles(new FilenameFilter(){
    public boolean accept(    File dir,    String name){
      return dir.isDirectory() && name.charAt(0) != '.';
    }
  }
);
}

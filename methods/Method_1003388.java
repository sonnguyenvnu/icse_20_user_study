/** 
 * Get the list of files in the given directory and all subdirectories.
 * @param dir the source directory
 * @return the file list
 */
protected FileList files(String dir){
  FileList list=new FileList();
  addFiles(list,new File(dir));
  return list;
}

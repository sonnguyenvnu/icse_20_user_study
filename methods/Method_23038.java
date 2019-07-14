/** 
 * Function to return the length of the file, or entire directory, including the component files and sub-folders if passed.
 * @param file The file or folder to calculate
 */
static public long calcSize(File file){
  return file.isFile() ? file.length() : Util.calcFolderSize(file);
}

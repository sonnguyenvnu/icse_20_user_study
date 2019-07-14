/** 
 * Searches for files in a given directory.
 * @param dir     the directory to search files
 * @param filter  the filename filter that can optionally be passed to get files that match this filter
 * @param recurse search for files recursively or not
 * @return list of files from the given directory
 */
public List<File> findFilesFrom(File dir,FilenameFilter filter,boolean recurse){
  this.filter=filter;
  List<File> files=new ArrayList<>();
  scanDirectory(dir,files,recurse);
  return files;
}

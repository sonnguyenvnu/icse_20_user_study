/** 
 * Puts folders before files.
 */
public FindFile sortFoldersFirst(){
  addComparator(new FolderFirstComparator(true));
  return this;
}

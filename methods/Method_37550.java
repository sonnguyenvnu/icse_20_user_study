/** 
 * Puts files before folders.
 */
public FindFile sortFoldersLast(){
  addComparator(new FolderFirstComparator(false));
  return this;
}

/** 
 * Sorts files by last modified time descending.
 */
public FindFile sortByTimeDesc(){
  addComparator(new FileLastModifiedTimeComparator(false));
  return this;
}

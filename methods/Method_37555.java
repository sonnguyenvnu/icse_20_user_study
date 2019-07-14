/** 
 * Sorts files by last modified time.
 */
public FindFile sortByTime(){
  addComparator(new FileLastModifiedTimeComparator(true));
  return this;
}

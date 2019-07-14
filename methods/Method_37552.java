/** 
 * Sorts files by file names descending, using <b>natural</b> sort.
 */
public FindFile sortByNameDesc(){
  addComparator(new FileNameComparator(false));
  return this;
}

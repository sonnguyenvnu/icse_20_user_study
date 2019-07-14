/** 
 * Sorts files by file name, using <b>natural</b> sort.
 */
public FindFile sortByName(){
  addComparator(new FileNameComparator(true));
  return this;
}

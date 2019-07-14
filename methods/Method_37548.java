/** 
 * Adds generic sorting.
 */
public FindFile sortWith(final Comparator<File> fileComparator){
  addComparator(fileComparator);
  return this;
}

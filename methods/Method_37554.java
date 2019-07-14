/** 
 * Sorts files by file extension descending.
 */
public FindFile sortByExtensionDesc(){
  addComparator(new FileExtensionComparator(false));
  return this;
}

/** 
 * Sorts files by file extension.
 */
public FindFile sortByExtension(){
  addComparator(new FileExtensionComparator(true));
  return this;
}

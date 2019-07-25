/** 
 * Returns iterator.
 * @param session the session
 * @param orderedRows ordered rows
 * @param sortOrder sort order
 * @param currentRow index of the current row
 * @param reverse whether iterator should iterate in reverse order
 * @return iterator
 */
public Iterator<Value[]> iterator(Session session,ArrayList<Value[]> orderedRows,SortOrder sortOrder,int currentRow,boolean reverse){
  int startIndex=getIndex(session,orderedRows,sortOrder,currentRow,starting,false);
  int endIndex=following != null ? getIndex(session,orderedRows,sortOrder,currentRow,following,true) : units == WindowFrameUnits.ROWS ? currentRow : toGroupEnd(orderedRows,sortOrder,currentRow,orderedRows.size() - 1);
  if (endIndex < startIndex) {
    return Collections.emptyIterator();
  }
  int size=orderedRows.size();
  if (startIndex >= size || endIndex < 0) {
    return Collections.emptyIterator();
  }
  if (startIndex < 0) {
    startIndex=0;
  }
  if (endIndex >= size) {
    endIndex=size - 1;
  }
  return exclusion != WindowFrameExclusion.EXCLUDE_NO_OTHERS ? complexIterator(orderedRows,sortOrder,currentRow,startIndex,endIndex,reverse) : plainIterator(orderedRows,startIndex,endIndex,reverse);
}

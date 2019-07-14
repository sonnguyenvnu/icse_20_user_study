/** 
 * Same as getColumnIndex(), but creates the column if it doesn't exist. Named this way to not conflict with checkColumn(), an internal function used to ensure that a columns exists, and also to denote that it returns an int for the column index.
 * @param title column title
 * @return index of a new or previously existing column
 */
public int checkColumnIndex(String title){
  int index=getColumnIndex(title,false);
  if (index != -1) {
    return index;
  }
  addColumn(title);
  return getColumnCount() - 1;
}

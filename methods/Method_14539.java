/** 
 * Copy a row and return the copy. Note that this is a shallow copy, so if the contents of cells are changed in the original, they will be be changed in the duplicate.
 * @return the duplicated row
 */
public Row dup(){
  Row row=new Row(cells.size());
  row.flagged=flagged;
  row.starred=starred;
  row.cells.addAll(cells);
  return row;
}

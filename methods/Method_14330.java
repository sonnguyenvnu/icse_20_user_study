/** 
 * Splits the line into columns
 * @param line Line to be split
 * @param widths array of integers with field sizes
 * @return
 */
static private ArrayList<Object> getCells(String line,int[] widths){
  ArrayList<Object> cells=new ArrayList<Object>();
  int columnStartCursor=0;
  int columnEndCursor=0;
  for (  int width : widths) {
    if (columnStartCursor >= line.length()) {
      cells.add(null);
      continue;
    }
    columnEndCursor=columnStartCursor + width;
    if (columnEndCursor > line.length()) {
      columnEndCursor=line.length();
    }
    if (columnEndCursor <= columnStartCursor) {
      cells.add(null);
      continue;
    }
    cells.add(line.substring(columnStartCursor,columnEndCursor));
    columnStartCursor=columnEndCursor;
  }
  if (columnStartCursor < line.length()) {
    cells.add(line.substring(columnStartCursor));
  }
  return cells;
}

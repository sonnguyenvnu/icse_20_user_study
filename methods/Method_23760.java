/** 
 * Remove the first row from the data set, and use it as the column titles. Use loadTable("table.csv", "header") instead.
 */
@Deprecated public String[] removeTitleRow(){
  String[] titles=getStringRow(0);
  removeRow(0);
  setColumnTitles(titles);
  return titles;
}

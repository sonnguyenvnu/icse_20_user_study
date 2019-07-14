/** 
 * ??
 * @param column
 * @return column.isEmpty() ? "*" : column;
 */
public static String column(String column){
  column=StringUtil.getTrimedString(column);
  return column.isEmpty() ? "*" : column;
}

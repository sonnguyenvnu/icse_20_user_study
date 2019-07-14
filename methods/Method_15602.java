/** 
 * ??
 * @param column if (StringUtil.isEmpty(column, true) || column.contains(",")) -> column = null;
 * @return " " + fun + "(" + {@link #column(String)} + ") ";
 */
public static String function(String fun,String column){
  if (StringUtil.isEmpty(column,true) || column.contains(",")) {
    column=null;
  }
  return " " + fun + "(" + column(column) + ") ";
}

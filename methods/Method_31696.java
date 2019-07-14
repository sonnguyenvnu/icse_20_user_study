/** 
 * @return The table rendered with column header and row data.
 */
public String render(){
  List<Integer> widths=new ArrayList<>();
  for (  String column : columns) {
    widths.add(column.length());
  }
  for (  List<String> row : rows) {
    for (int i=0; i < row.size(); i++) {
      widths.set(i,Math.max(widths.get(i),getValue(row,i).length()));
    }
  }
  StringBuilder ruler=new StringBuilder("+");
  for (  Integer width : widths) {
    ruler.append("-").append(StringUtils.trimOrPad("",width,'-')).append("-+");
  }
  ruler.append("\n");
  StringBuilder result=new StringBuilder();
  if (printHeader) {
    StringBuilder header=new StringBuilder("|");
    for (int i=0; i < widths.size(); i++) {
      header.append(" ").append(StringUtils.trimOrPad(columns.get(i),widths.get(i),' ')).append(" |");
    }
    header.append("\n");
    result.append(ruler);
    result.append(header);
  }
  result.append(ruler);
  if (rows.isEmpty()) {
    result.append("| ").append(StringUtils.trimOrPad(emptyText,ruler.length() - 5)).append(" |\n");
  }
 else {
    for (    List<String> row : rows) {
      StringBuilder r=new StringBuilder("|");
      for (int i=0; i < widths.size(); i++) {
        r.append(" ").append(StringUtils.trimOrPad(getValue(row,i),widths.get(i),' ')).append(" |");
      }
      r.append("\n");
      result.append(r);
    }
  }
  result.append(ruler);
  return result.toString();
}

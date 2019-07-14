private TableModel tableModelFrom(final List<Match> items){
  return new SortingTableModel<Match>(){
    @Override public Object getValueAt(    int rowIndex,    int columnIndex){
      Match match=items.get(rowIndex);
switch (columnIndex) {
case 0:
        return match.getLabel();
case 2:
      return Integer.toString(match.getLineCount());
case 1:
    return match.getMarkCount() > 2 ? Integer.toString(match.getMarkCount()) : "";
case 99:
  return match;
default :
return "";
}
}
@Override public int getColumnCount(){
return matchColumns.length;
}
@Override public int getRowCount(){
return items.size();
}
@Override public boolean isCellEditable(int rowIndex,int columnIndex){
return false;
}
@Override public Class<?> getColumnClass(int columnIndex){
return Object.class;
}
@Override public String getColumnName(int i){
return matchColumns[i].label();
}
@Override public int sortColumn(){
return sortColumn;
}
@Override public void sortColumn(int column){
sortColumn=column;
}
@Override public boolean sortDescending(){
return sortDescending;
}
@Override public void sortDescending(boolean flag){
sortDescending=flag;
}
@Override public void sort(Comparator<Match> comparator){
Collections.sort(items,comparator);
if (sortDescending) {
Collections.reverse(items);
}
}
}
;
}

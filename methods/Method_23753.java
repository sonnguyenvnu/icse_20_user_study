/** 
 * @param column the index number of the column to be removed
 */
public void removeColumn(int column){
  int newCount=columns.length - 1;
  Object[] columnsTemp=new Object[newCount];
  HashMapBlows[] catTemp=new HashMapBlows[newCount];
  for (int i=0; i < column; i++) {
    columnsTemp[i]=columns[i];
    catTemp[i]=columnCategories[i];
  }
  for (int i=column; i < newCount; i++) {
    columnsTemp[i]=columns[i + 1];
    catTemp[i]=columnCategories[i + 1];
  }
  columns=columnsTemp;
  columnCategories=catTemp;
  if (columnTitles != null) {
    String[] titlesTemp=new String[newCount];
    for (int i=0; i < column; i++) {
      titlesTemp[i]=columnTitles[i];
    }
    for (int i=column; i < newCount; i++) {
      titlesTemp[i]=columnTitles[i + 1];
    }
    columnTitles=titlesTemp;
    columnIndices=null;
  }
}

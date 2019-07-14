public void replaceAll(String regex,String replacement,int column){
  checkColumn(column);
  if (columnTypes[column] == STRING) {
    String[] stringData=(String[])columns[column];
    for (int row=0; row < rowCount; row++) {
      if (stringData[row] != null) {
        stringData[row]=stringData[row].replaceAll(regex,replacement);
      }
    }
  }
 else {
    throw new IllegalArgumentException("replaceAll() can only be used on String columns");
  }
}

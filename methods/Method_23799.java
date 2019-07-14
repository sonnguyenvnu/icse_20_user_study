public void replace(String orig,String replacement,int col){
  if (columnTypes[col] == STRING) {
    String[] stringData=(String[])columns[col];
    if (orig != null) {
      for (int row=0; row < rowCount; row++) {
        if (orig.equals(stringData[row])) {
          stringData[row]=replacement;
        }
      }
    }
 else {
      for (int row=0; row < rowCount; row++) {
        if (stringData[row] == null) {
          stringData[row]=replacement;
        }
      }
    }
  }
}

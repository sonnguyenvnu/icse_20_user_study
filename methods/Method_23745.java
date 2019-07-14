protected void writeTSV(PrintWriter writer){
  if (columnTitles != null) {
    for (int col=0; col < columns.length; col++) {
      if (col != 0) {
        writer.print('\t');
      }
      if (columnTitles[col] != null) {
        writer.print(columnTitles[col]);
      }
    }
    writer.println();
  }
  for (int row=0; row < rowCount; row++) {
    for (int col=0; col < getColumnCount(); col++) {
      if (col != 0) {
        writer.print('\t');
      }
      String entry=getString(row,col);
      if (entry != null) {
        writer.print(entry);
      }
    }
    writer.println();
  }
  writer.flush();
}

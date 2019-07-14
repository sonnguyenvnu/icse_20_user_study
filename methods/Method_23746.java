protected void writeCSV(PrintWriter writer){
  if (columnTitles != null) {
    for (int col=0; col < getColumnCount(); col++) {
      if (col != 0) {
        writer.print(',');
      }
      try {
        if (columnTitles[col] != null) {
          writeEntryCSV(writer,columnTitles[col]);
        }
      }
 catch (      ArrayIndexOutOfBoundsException e) {
        PApplet.printArray(columnTitles);
        PApplet.printArray(columns);
        throw e;
      }
    }
    writer.println();
  }
  for (int row=0; row < rowCount; row++) {
    for (int col=0; col < getColumnCount(); col++) {
      if (col != 0) {
        writer.print(',');
      }
      String entry=getString(row,col);
      if (entry != null) {
        writeEntryCSV(writer,entry);
      }
    }
    writer.println();
  }
  writer.flush();
}

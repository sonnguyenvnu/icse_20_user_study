protected void parseBasic(BufferedReader reader,boolean header,boolean tsv) throws IOException {
  String line=null;
  int row=0;
  if (rowCount == 0) {
    setRowCount(10);
  }
  try {
    while ((line=reader.readLine()) != null) {
      if (row == getRowCount()) {
        setRowCount(row << 1);
      }
      if (row == 0 && header) {
        setColumnTitles(tsv ? PApplet.split(line,'\t') : splitLineCSV(line,reader));
        header=false;
      }
 else {
        setRow(row,tsv ? PApplet.split(line,'\t') : splitLineCSV(line,reader));
        row++;
      }
      if (row % 10000 == 0) {
        try {
          Thread.sleep(10);
        }
 catch (        InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
 catch (  Exception e) {
    throw new RuntimeException("Error reading table on line " + row,e);
  }
  if (row != getRowCount()) {
    setRowCount(row);
  }
}

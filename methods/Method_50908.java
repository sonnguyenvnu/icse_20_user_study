private void copyMatchListSelectionsToClipboard(){
  int[] selectionIndices=resultsTable.getSelectedRows();
  int colCount=resultsTable.getColumnCount();
  StringBuilder sb=new StringBuilder();
  for (int r=0; r < selectionIndices.length; r++) {
    if (r > 0) {
      sb.append('\n');
    }
    sb.append(resultsTable.getValueAt(selectionIndices[r],0));
    for (int c=1; c < colCount; c++) {
      sb.append('\t');
      sb.append(resultsTable.getValueAt(selectionIndices[r],c));
    }
  }
  StringSelection ss=new StringSelection(sb.toString());
  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,null);
}

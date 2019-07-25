public void merge(StyleTableRowProperties tableRowProperties){
  if (tableRowProperties.getKeepTogether() != null) {
    keepTogether=tableRowProperties.getKeepTogether();
  }
  if (tableRowProperties.getMinRowHeight() != null) {
    minRowHeight=tableRowProperties.getMinRowHeight();
  }
  if (tableRowProperties.getRowHeight() != null) {
    rowHeight=tableRowProperties.getRowHeight();
  }
}

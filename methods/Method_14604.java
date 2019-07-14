@Override protected String getBriefDescription(Project project){
  return _clearData ? "Discard recon judgments and clear recon data for cells in column " + _columnName : "Discard recon judgments for cells in column " + _columnName;
}

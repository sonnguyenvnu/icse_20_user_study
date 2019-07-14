protected boolean matchRow(Project project,int rowIndex,Row row){
  for (  RowFilter rowFilter : _rowFilters) {
    if (!rowFilter.filterRow(project,rowIndex,row)) {
      return false;
    }
  }
  return true;
}

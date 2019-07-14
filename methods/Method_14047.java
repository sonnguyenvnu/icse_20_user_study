@Override public void computeChoices(Project project,FilteredRows filteredRows){
  if (_eval != null && _errorMessage == null) {
    ExpressionNominalValueGrouper grouper=new ExpressionNominalValueGrouper(_eval,_config.columnName,_cellIndex);
    filteredRows.accept(project,grouper);
    postProcessGrouper(grouper);
  }
}

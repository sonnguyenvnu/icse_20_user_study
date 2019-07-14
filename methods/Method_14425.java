protected void computeJoin(ProjectJoin join) throws JoinException {
  if (join.fromProjectID < 0 || join.toProjectID < 0) {
    return;
  }
  Project fromProject=ProjectManager.singleton.getProject(join.fromProjectID);
  ProjectMetadata fromProjectMD=ProjectManager.singleton.getProjectMetadata(join.fromProjectID);
  Project toProject=ProjectManager.singleton.getProject(join.toProjectID);
  ProjectMetadata toProjectMD=ProjectManager.singleton.getProjectMetadata(join.toProjectID);
  if (fromProject == null || toProject == null) {
    return;
  }
  Column fromColumn=fromProject.columnModel.getColumnByName(join.fromProjectColumnName);
  Column toColumn=toProject.columnModel.getColumnByName(join.toProjectColumnName);
  if (fromColumn == null) {
    throw new JoinException("Unable to find column " + join.fromProjectColumnName + " in project " + fromProjectMD.getName());
  }
  if (toColumn == null) {
    throw new JoinException("Unable to find column " + join.toProjectColumnName + " in project " + toProjectMD.getName());
  }
  for (  Row fromRow : fromProject.rows) {
    Object value=fromRow.getCellValue(fromColumn.getCellIndex());
    if (ExpressionUtils.isNonBlankData(value) && !join.valueToRowIndices.containsKey(value)) {
      join.valueToRowIndices.put(value,new ArrayList<Integer>());
    }
  }
  int count=toProject.rows.size();
  for (int r=0; r < count; r++) {
    Row toRow=toProject.rows.get(r);
    Object value=toRow.getCellValue(toColumn.getCellIndex());
    if (ExpressionUtils.isNonBlankData(value) && join.valueToRowIndices.containsKey(value)) {
      join.valueToRowIndices.get(value).add(r);
    }
  }
}

@Override public void revert(Project project){
  Row row=project.rows.get(rowIndex);
  row.starred=oldStarred;
}

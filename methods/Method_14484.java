@Override public void apply(Project project){
  Row row=project.rows.get(rowIndex);
  if (oldStarred == null) {
    oldStarred=row.starred;
  }
  row.starred=newStarred;
}

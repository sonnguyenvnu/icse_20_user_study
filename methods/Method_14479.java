@Override public void apply(Project project){
  Row row=project.rows.get(rowIndex);
  if (oldFlagged == null) {
    oldFlagged=row.flagged;
  }
  row.flagged=newFlagged;
}

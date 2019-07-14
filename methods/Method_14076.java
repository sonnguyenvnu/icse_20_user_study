@Override public void accept(Project project,RowVisitor visitor){
  try {
    visitor.start(project);
    int c=project.rows.size();
    for (int rowIndex=0; rowIndex < c; rowIndex++) {
      Row row=project.rows.get(rowIndex);
      if (matchRow(project,rowIndex,row)) {
        if (visitRow(project,visitor,rowIndex,row)) {
          break;
        }
      }
    }
  }
  finally {
    visitor.end(project);
  }
}

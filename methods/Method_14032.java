@JsonIgnore public FilteredRows getAllRows(){
  return new FilteredRows(){
    @Override public void accept(    Project project,    RowVisitor visitor){
      try {
        visitor.start(project);
        int c=project.rows.size();
        for (int rowIndex=0; rowIndex < c; rowIndex++) {
          Row row=project.rows.get(rowIndex);
          if (visitor.visit(project,rowIndex,row)) {
            break;
          }
        }
      }
  finally {
        visitor.end(project);
      }
    }
  }
;
}

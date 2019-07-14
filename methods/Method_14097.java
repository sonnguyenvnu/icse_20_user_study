@Override protected void iterate(Project project,RowEvaluable rowEvaluable,List<Long> allValues){
  Properties bindings=ExpressionUtils.createBindings(project);
  for (int i=0; i < project.rows.size(); i++) {
    Row row=project.rows.get(i);
    preprocessing();
    processRow(project,rowEvaluable,allValues,i,row,bindings);
    postprocessing();
  }
}

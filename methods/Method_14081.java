protected void visitRow(Project project,int rowIndex,Row row,Properties bindings,int index){
  Object value=evalRow(project,rowIndex,row,bindings);
  if (value != null) {
    if (value.getClass().isArray()) {
      Object[] a=(Object[])value;
      for (      Object v : a) {
        processValue(v,rowIndex);
      }
    }
 else     if (value instanceof Collection<?>) {
      for (      Object v : ExpressionUtils.toObjectCollection(value)) {
        processValue(v,rowIndex);
      }
    }
 else {
      processValue(value,rowIndex);
    }
  }
 else {
    processValue(value,rowIndex);
  }
}

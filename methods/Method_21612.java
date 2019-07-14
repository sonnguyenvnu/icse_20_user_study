private void findLimit(SQLLimit limit,Query query){
  if (limit == null) {
    return;
  }
  query.setRowCount(Integer.parseInt(limit.getRowCount().toString()));
  if (limit.getOffset() != null)   query.setOffset(Integer.parseInt(limit.getOffset().toString()));
}

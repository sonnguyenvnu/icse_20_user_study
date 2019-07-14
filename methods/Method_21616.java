private void updateJoinLimit(SQLLimit limit,JoinSelect joinSelect){
  if (limit != null && limit.getRowCount() != null) {
    int sizeLimit=Integer.parseInt(limit.getRowCount().toString());
    joinSelect.setTotalLimit(sizeLimit);
  }
}

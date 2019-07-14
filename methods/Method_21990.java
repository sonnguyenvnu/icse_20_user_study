private String buildLimit(final int page,final int perPage){
  StringBuilder sqlBuilder=new StringBuilder();
  if (page > 0 && perPage > 0) {
    sqlBuilder.append(" LIMIT ").append((page - 1) * perPage).append(",").append(perPage);
  }
 else {
    sqlBuilder.append(" LIMIT ").append(Condition.DEFAULT_PAGE_SIZE);
  }
  return sqlBuilder.toString();
}

@Override public List<TableMetadata> parseAll() throws SQLException {
  String dsId=DataSourceHolder.switcher().currentDataSourceId();
  return sqlExecutor.list(getSelectAllTableSql()).parallelStream().map(map -> map.get("name")).map(String::valueOf).map(tableName -> {
    try {
      DataSourceHolder.switcher().use(dsId);
      return this.parse(tableName);
    }
  finally {
      DataSourceHolder.switcher().reset();
    }
  }
).filter(Objects::nonNull).collect(Collectors.toList());
}

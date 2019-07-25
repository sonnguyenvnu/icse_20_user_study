/** 
 * Check mandatory properties.
 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
 */
@Override public void init(DataSource dataSource) throws Exception {
  Assert.notNull(dataSource,"A DataSource is required");
  Assert.hasLength(selectClause,"selectClause must be specified");
  Assert.hasLength(fromClause,"fromClause must be specified");
  Assert.notEmpty(sortKeys,"sortKey must be specified");
  StringBuilder sql=new StringBuilder();
  sql.append("SELECT ").append(selectClause);
  sql.append(" FROM ").append(fromClause);
  if (whereClause != null) {
    sql.append(" WHERE ").append(whereClause);
  }
  if (groupClause != null) {
    sql.append(" GROUP BY ").append(groupClause);
  }
  List<String> namedParameters=new ArrayList<>();
  parameterCount=JdbcParameterUtils.countParameterPlaceholders(sql.toString(),namedParameters);
  if (namedParameters.size() > 0) {
    if (parameterCount != namedParameters.size()) {
      throw new InvalidDataAccessApiUsageException("You can't use both named parameters and classic \"?\" placeholders: " + sql);
    }
    usingNamedParameters=true;
  }
}

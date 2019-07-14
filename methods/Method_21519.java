private Where createWhereWithOrigianlAndTermsFilter(String secondFieldName,Where originalWhereSecondTable,Set<Object> currentSetFromResults) throws SqlParseException {
  Where where=Where.newInstance();
  where.setConn(Where.CONN.AND);
  where.addWhere(originalWhereSecondTable);
  where.addWhere(buildTermsFilterFromResults(currentSetFromResults,secondFieldName));
  return where;
}

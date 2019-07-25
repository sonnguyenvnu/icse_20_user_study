@Override public Query parse(String query) throws ParseException {
  if (query.trim().isEmpty()) {
    return queryBuilder.zeroTermsQuery();
  }
  return super.parse(query);
}

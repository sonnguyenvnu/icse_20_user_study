private String explain(SearchContext context,boolean rewritten) throws IOException {
  Query query=context.query();
  if (rewritten && query instanceof MatchNoDocsQuery) {
    return context.parsedQuery().query().toString();
  }
 else {
    return query.toString();
  }
}

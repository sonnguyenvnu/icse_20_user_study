@Override public QueryResult createQueryResult(HttpResponse res,Query query) throws TwitterException {
  try {
    return new QueryResultJSONImpl(res,conf);
  }
 catch (  TwitterException te) {
    if (404 == te.getStatusCode()) {
      return new QueryResultJSONImpl(query);
    }
 else {
      throw te;
    }
  }
}

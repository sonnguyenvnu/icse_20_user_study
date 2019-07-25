@Override public final DirectoryReader wrap(final DirectoryReader in) throws IOException {
  ShardSearchRequest request=current();
  if (request != null && !Boolean.FALSE.equals(request.tokenRangesBitsetCache()) && request.tokenRanges() != null) {
    Query tokenRangeQuery=tokenRangesService.getTokenRangesQuery(request.tokenRanges());
    if (tokenRangeQuery != null) {
      BooleanQuery.Builder qb=new BooleanQuery.Builder().add(tokenRangeQuery,Occur.FILTER);
      Query query=query(qb);
      return new TokenRangesDirectoryReader(in,query,this.filterCache);
    }
  }
  return in;
}

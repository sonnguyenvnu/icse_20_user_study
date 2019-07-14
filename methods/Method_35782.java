@Override public ListStubMappingsResult findAllStubsByMetadata(StringValuePattern pattern){
  return new ListStubMappingsResult(LimitAndOffsetPaginator.none(stubMappings.findByMetadata(pattern)));
}

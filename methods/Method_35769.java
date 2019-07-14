@Override public ListStubMappingsResult listAllStubMappings(){
  return new ListStubMappingsResult(LimitAndOffsetPaginator.none(stubMappings.getAll()));
}

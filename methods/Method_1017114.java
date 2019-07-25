@POST @Path("keys") public void keys(@Suspended final AsyncResponse response,final MetadataQueryBody request) throws ExecutionException {
  final RequestCriteria c=toCriteria(request::getFilter,request::getRange,() -> OptionalLimit.of(request.getLimit().orElse(MetadataQueryBody.DEFAULT_LIMIT)));
  httpAsync.bind(response,cache.findKeys(Optional.empty(),new FindKeys.Request(c.getFilter(),c.getRange(),c.getLimit())));
}

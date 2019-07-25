@POST @Path("tags") public void tags(@Suspended final AsyncResponse response,final MetadataQueryBody request) throws ExecutionException {
  final RequestCriteria c=toCriteria(request::getFilter,request::getRange,() -> OptionalLimit.of(request.getLimit().orElse(MetadataQueryBody.DEFAULT_LIMIT)));
  httpAsync.bind(response,cache.findTags(Optional.empty(),new FindTags.Request(c.getFilter(),c.getRange(),c.getLimit())));
}

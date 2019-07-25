/** 
 * Converts an entity request to a batch one, for subsequent batching with other requests.
 * @param request to convert
 * @param < RT > type of entity template
 * @return batch request
 */
@SuppressWarnings("deprecation") public static <RT extends RecordTemplate>BatchGetRequest<RT> batch(GetRequest<RT> request){
  Object id=request.getObjectId();
  if (id == null) {
    throw new IllegalArgumentException("It is not possible to create a batch get request from a get request without an id.");
  }
  Class<?> keyClass=request.getResourceSpec().getKeyClass();
  throwIfClassCompoundOrComplex(keyClass,"batch","batchKV");
  Map<String,Object> queryParams=new HashMap<String,Object>(request.getQueryParamsObjects());
  queryParams.put(RestConstants.QUERY_BATCH_IDS_PARAM,new HashSet<>(Arrays.asList(id)));
  return new BatchGetRequest<RT>(getReadOnlyHeaders(request.getHeaders()),getReadOnlyCookies(request.getCookies()),new BatchResponseDecoder<RT>(request.getEntityClass()),getReadOnlyQueryParameters(queryParams),Collections.<String,Class<?>>emptyMap(),request.getResourceSpec(),request.getBaseUriTemplate(),getReadOnlyPathKeys(request.getPathKeys()),request.getRequestOptions());
}

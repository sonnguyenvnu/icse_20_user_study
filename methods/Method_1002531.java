/** 
 * This method provides granular exception messages for developers to know why a request intended to be added to a batch, might fail to be batched.
 * @param request the request to validate is the same or not
 * @throws IllegalArgumentException if there are any problems
 */
public <T>void validate(BatchRequest<T> request){
  if (!request.getBaseUriTemplate().equals(_request.getBaseUriTemplate()) || !request.getPathKeys().equals(_request.getPathKeys())) {
    throw new IllegalArgumentException("Requests must have same base URI template and path keys to batch");
  }
  if (!request.getResourceProperties().equals(_request.getResourceProperties())) {
    throw new IllegalArgumentException("Requests must be for the same resource to batch");
  }
  if (!request.getRequestOptions().equals(_request.getRequestOptions())) {
    throw new IllegalArgumentException("Requests must have the same RestliRequestOptions to batch!");
  }
  final Map<String,Object> queryParams=BatchGetRequestUtil.getQueryParamsForBatchingKey(request);
  if (!queryParams.equals(_queryParams)) {
    throw new IllegalArgumentException("Requests must have same parameters to batch");
  }
  if (!_batchFields) {
    if (!request.getFields().equals(_request.getFields())) {
      throw new IllegalArgumentException("Requests must have same fields to batch");
    }
  }
}

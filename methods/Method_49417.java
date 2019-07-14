public Map<String,Object> createRequestBody(ElasticSearchRequest request,Parameter[] parameters){
  final Map<String,Object> requestBody=new HashMap<>();
  Optional.ofNullable(request.getSize()).ifPresent(parameter -> requestBody.put("size",parameter));
  Optional.ofNullable(request.getFrom()).ifPresent(parameter -> requestBody.put("from",parameter));
  if (!request.getSorts().isEmpty()) {
    requestBody.put("sort",request.getSorts());
  }
  Optional.ofNullable(request.getQuery()).ifPresent(parameter -> requestBody.put("query",parameter));
  Optional.ofNullable(parameters).ifPresent(p -> Arrays.stream(p).forEachOrdered(parameter -> requestBody.put(parameter.key(),parameter.value())));
  return requestBody;
}

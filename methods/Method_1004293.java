static Response strip(Response response){
  return response != null && response.body() != null ? response.newBuilder().body(null).networkResponse(null).cacheResponse(null).build() : response;
}

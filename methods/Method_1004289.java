private Response prefetch(Response networkResponse,String cacheKey) throws IOException {
  if (!networkResponse.isSuccessful()) {
    return networkResponse;
  }
  try {
    cache.write(networkResponse,cacheKey);
  }
  finally {
    networkResponse.close();
  }
  Response cachedResponse=cache.read(cacheKey);
  if (cachedResponse == null) {
    throw new IOException("failed to read prefetch cache response");
  }
  return cachedResponse.newBuilder().networkResponse(strip(networkResponse)).build();
}

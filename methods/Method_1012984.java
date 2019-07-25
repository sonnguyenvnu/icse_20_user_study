@Override public Exception decode(String methodKey,Response response){
  return new ZallyClientError(response.reason());
}

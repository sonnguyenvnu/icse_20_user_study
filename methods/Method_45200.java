public static ResponseHandler with(final Resource resource){
  return responseHandler(checkNotNull(resource,"Resource should not be null"));
}

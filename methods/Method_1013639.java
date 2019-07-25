@Override public boolean retry(Throwable e){
  if (e instanceof ResourceAccessException) {
    return true;
  }
  if (e instanceof HttpServerErrorException) {
    HttpStatus statusCode=((HttpServerErrorException)e).getStatusCode();
    if (statusCode == HttpStatus.BAD_GATEWAY) {
      return true;
    }
  }
  return false;
}

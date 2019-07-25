@Override public boolean retry(Throwable e){
  if (e instanceof ResourceAccessException || e instanceof HttpServerErrorException || e instanceof TimeoutException) {
    return true;
  }
  return false;
}

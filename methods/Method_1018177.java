private <R>Optional<R> optinal(Supplier<R> supplier){
  try {
    return Optional.ofNullable(supplier.get());
  }
 catch (  HttpClientErrorException e) {
    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
      return Optional.empty();
    }
 else {
      throw e;
    }
  }
}

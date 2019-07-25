private <R>R nullable(Supplier<R> supplier){
  try {
    return supplier.get();
  }
 catch (  HttpClientErrorException e) {
    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
      return null;
    }
 else {
      throw e;
    }
  }
}

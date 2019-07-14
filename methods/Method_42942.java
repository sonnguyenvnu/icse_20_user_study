protected static void throwErrors(BiboxResponse<?> response){
  if (response.getError() != null) {
    throw new ExchangeException(response.getError().getCode() + ": " + response.getError().getMsg());
  }
}

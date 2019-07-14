public static <D>CobinhoodResponse<D> checkSuccess(CobinhoodResponse<D> response){
  if (response.isSuccess()) {
    return response;
  }
 else {
    throw new ExchangeException(response.getError().getCode());
  }
}

public static <T extends CoinbeneResponse>T checkSuccess(T response){
  if (response.isOk()) {
    return response;
  }
 else {
    throw new CoinbeneException(response.getErrorDescription());
  }
}

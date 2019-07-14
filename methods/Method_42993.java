private <T>T checkWapiResponse(WapiResponse<T> result){
  if (!result.success) {
    BinanceException exception;
    try {
      exception=new ObjectMapper().readValue(result.msg,BinanceException.class);
    }
 catch (    Throwable e) {
      exception=new BinanceException(-1,result.msg);
    }
    throw exception;
  }
  return result.getData();
}

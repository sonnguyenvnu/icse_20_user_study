protected SimpleOAuth2Response createNativeResponse(Supplier<Response> responseSupplier){
  SimpleOAuth2Response response=new SimpleOAuth2Response(responseSupplier.get(),convertHandler,responseJudge);
  return auth2Response=response;
}

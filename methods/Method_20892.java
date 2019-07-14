public static @NonNull ApiException badRequestException(){
  final ErrorEnvelope envelope=ErrorEnvelope.builder().errorMessages(Collections.singletonList("bad request")).httpCode(400).build();
  final ResponseBody body=ResponseBody.create(null,"");
  final retrofit2.Response<Observable<User>> response=retrofit2.Response.error(400,body);
  return new ApiException(envelope,response);
}

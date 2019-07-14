public static @NonNull ApiException tfaFailed(){
  final ErrorEnvelope envelope=ErrorEnvelope.builder().ksrCode(ErrorEnvelope.TFA_FAILED).httpCode(400).build();
  final ResponseBody body=ResponseBody.create(null,new Gson().toJson(envelope));
  final retrofit2.Response<Observable<User>> response=retrofit2.Response.error(envelope.httpCode(),body);
  return new ApiException(envelope,response);
}

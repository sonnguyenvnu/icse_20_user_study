public static @NonNull ApiException tfaRequired(){
  final ErrorEnvelope envelope=ErrorEnvelope.builder().ksrCode(ErrorEnvelope.TFA_REQUIRED).httpCode(403).errorMessages(Collections.singletonList("Two-factor authentication required.")).build();
  final ResponseBody body=ResponseBody.create(null,new Gson().toJson(envelope));
  final retrofit2.Response<Observable<User>> response=retrofit2.Response.error(envelope.httpCode(),body);
  return new ApiException(envelope,response);
}

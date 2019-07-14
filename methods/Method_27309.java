@NonNull public static LoginRestService getLoginRestService(@NonNull String authToken,@Nullable String otp,@Nullable String endpoint){
  return provideRetrofit(authToken,otp,endpoint).create(LoginRestService.class);
}

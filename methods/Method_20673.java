public static @NonNull StripeToken create(final @NonNull String str,final @NonNull Gson gson){
  return gson.fromJson(str,StripeToken.class);
}

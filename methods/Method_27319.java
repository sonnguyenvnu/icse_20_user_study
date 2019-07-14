@NonNull public static UserRestService getUserService(boolean enterprise){
  return provideRetrofit(enterprise).create(UserRestService.class);
}

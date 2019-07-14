@NonNull public static OrganizationService getOrgService(boolean enterprise){
  return provideRetrofit(enterprise).create(OrganizationService.class);
}

@Override public boolean getHttpsRequiredForAdminApi(){
  return optionSet.has(ADMIN_API_REQUIRE_HTTPS);
}

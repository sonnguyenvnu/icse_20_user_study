public static boolean isOAuth(Authentication authentication){
  if (authentication instanceof OAuth2Authentication) {
    return true;
  }
  return false;
}

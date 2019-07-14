private boolean isAuthenticated(){
  Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
  if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
    return false;
  }
  return true;
}

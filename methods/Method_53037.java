public boolean isAuthenticated(){
  return request.getAuthorization().isEnabled();
}

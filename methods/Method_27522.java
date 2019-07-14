private boolean validateAuth(){
  if (!isSecured()) {
    if (!isLoggedIn()) {
      onRequireLogin();
      return false;
    }
  }
  return true;
}

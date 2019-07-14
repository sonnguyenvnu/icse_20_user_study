protected boolean verifyHttpLogin(int code){
  if (isAlive() == false) {
    return true;
  }
  if (code == JSONResponse.CODE_NOT_LOGGED_IN) {
    APIJSONApplication.getInstance().logout();
    setCurrentUser();
  }
  return verifyLogin();
}

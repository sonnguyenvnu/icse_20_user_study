/** 
 * ????toLoginActivity();
 * @return isLoggedIn
 */
protected boolean verifyLogin(){
  if (isLoggedIn == false) {
    showShortToast("????");
    toLoginActivity();
  }
  return isLoggedIn;
}

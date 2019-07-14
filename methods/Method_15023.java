/** 
 * ????????????????????
 * @return
 */
private boolean isLoggedIn(){
  boolean isLoggedIn=APIJSONApplication.getInstance().isLoggedIn();
  if (isLoggedIn == false) {
    context.startActivity(LoginActivity.createIntent(context));
    context.overridePendingTransition(R.anim.bottom_push_in,R.anim.hold);
  }
  return isLoggedIn;
}

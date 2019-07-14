protected void toLoginActivity(){
  startActivity(LoginActivity.createIntent(context));
  context.overridePendingTransition(R.anim.bottom_push_in,R.anim.hold);
}

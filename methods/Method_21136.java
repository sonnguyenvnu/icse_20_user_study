private void startSignup(){
  final Intent intent=new Intent(this,SignupActivity.class);
  startActivityForResult(intent,ActivityRequestCodes.LOGIN_FLOW);
  transition(this,fadeIn());
}

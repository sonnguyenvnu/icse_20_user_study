private void startLoginToutActivity(){
  final Intent intent=new Intent(this,LoginToutActivity.class).putExtra(IntentKey.LOGIN_REASON,LoginReason.DEFAULT);
  startActivityForResult(intent,ActivityRequestCodes.LOGIN_FLOW);
  transition(this,slideInFromRight());
}

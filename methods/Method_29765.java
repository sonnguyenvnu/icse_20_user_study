private Intent makeBooleanIntent(boolean result){
  Intent intent=new Intent();
  intent.putExtra(AccountManager.KEY_BOOLEAN_RESULT,result);
  return intent;
}

private Bundle makeErrorBundle(int errorCode,String errorMessage){
  Bundle bundle=new Bundle();
  bundle.putInt(AccountManager.KEY_ERROR_CODE,errorCode);
  bundle.putString(AccountManager.KEY_ERROR_MESSAGE,errorMessage);
  return bundle;
}

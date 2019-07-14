private Bundle makeIntentBundle(Intent intent){
  Bundle bundle=new Bundle();
  bundle.putParcelable(AccountManager.KEY_INTENT,intent);
  return bundle;
}

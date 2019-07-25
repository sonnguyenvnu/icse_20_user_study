public void login(int accountIndex,AutoLoginCallback cb){
  mState=PROCESSING;
  mCallback=cb;
  mAccountManager.getAuthToken(mAccounts[accountIndex],mAuthToken,null,mActivity,this,null);
}

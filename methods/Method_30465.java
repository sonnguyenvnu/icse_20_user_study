public void onAccountListChanged(){
  boolean needReload=!mActiveAccount.equals(AccountUtils.getActiveAccount());
  bind();
  if (mListener != null && needReload) {
    mListener.onAccountTransitionStart();
    mListener.onAccountTransitionEnd();
  }
}

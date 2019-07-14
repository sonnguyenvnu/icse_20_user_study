@Override public void onAccountsUpdated(Account[] accounts){
  if (AccountUtils.getActiveAccount() == null) {
    AccountUtils.ensureActiveAccountAvailability(getActivity());
    return;
  }
  if (isResumed()) {
    onAccountListChanged();
  }
 else {
    mHasPendingAccountListChange=true;
  }
}

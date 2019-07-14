public void switchToAccountWithTransitionIfNotRunning(Account account){
  if (mAccountTransitionRunning) {
    return;
  }
  showAccountList(false);
  if (AccountUtils.isActiveAccount(account)) {
    return;
  }
  AccountUtils.setActiveAccount(account);
  if (account.equals(mRecentOneAccount)) {
    beginAvatarTransitionFromRecent(mRecentOneAvatarImage);
  }
 else   if (account.equals(mRecentTwoAccount)) {
    beginAvatarTransitionFromRecent(mRecentTwoAvatarImage);
  }
 else {
    beginAvatarTransitionFromNonRecent();
  }
  bind();
}

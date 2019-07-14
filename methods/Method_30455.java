public void bind(){
  List<Account> accountList=new ArrayList<>(Arrays.asList(AccountUtils.getAccounts()));
  accountList.remove(AccountUtils.getActiveAccount());
  Account recentOneAccount=AccountUtils.getRecentOneAccount();
  if (recentOneAccount != null) {
    accountList.remove(recentOneAccount);
  }
  Account recentTwoAccount=AccountUtils.getRecentTwoAccount();
  if (recentTwoAccount != null) {
    accountList.remove(recentTwoAccount);
  }
  if (recentOneAccount != null) {
    accountList.add(recentOneAccount);
  }
  if (recentTwoAccount != null) {
    accountList.add(recentTwoAccount);
  }
  int i=0;
  for (  final Account account : accountList) {
    if (i >= mAccountList.getChildCount()) {
      ViewUtils.inflateInto(R.layout.navigation_account_item,mAccountList);
    }
    View accountLayout=mAccountList.getChildAt(i);
    accountLayout.setVisibility(VISIBLE);
    AccountLayoutHolder holder=(AccountLayoutHolder)accountLayout.getTag();
    if (holder == null) {
      holder=new AccountLayoutHolder(accountLayout);
      accountLayout.setTag(holder);
    }
    User user=mAdapter.getUser(account);
    if (user != null) {
      ImageUtils.loadNavigationAccountListAvatar(holder.avatarImage,user.getLargeAvatarOrAvatar());
    }
 else {
      holder.avatarImage.setImageResource(R.drawable.avatar_icon_40dp);
    }
    holder.nameText.setText(mAdapter.getPartialUser(account).name);
    accountLayout.setOnClickListener(view -> {
      if (mListener != null) {
        mListener.switchToAccount(account);
      }
    }
);
    ++i;
  }
  ViewUtils.setVisibleOrGone(mDividerView,i > 0);
  for (int count=mAccountList.getChildCount(); i < count; ++i) {
    mAccountList.getChildAt(i).setVisibility(GONE);
  }
}

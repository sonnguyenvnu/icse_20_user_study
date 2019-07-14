private void onAccountListChanged(){
  ArrayMap<Account,AccountUserResource> oldUserResourceMap=mUserResourceMap;
  mUserResourceMap=new ArrayMap<>();
  for (  Account account : AccountUtils.getAccounts()) {
    mUserResourceMap.put(account,AccountUserResource.attachTo(account,this,account.name,-1));
    oldUserResourceMap.remove(account);
  }
  for (  AccountUserResource userResource : oldUserResourceMap.values()) {
    userResource.detach();
  }
  mHeaderLayout.onAccountListChanged();
  mNavigationViewAdapter.onAccountListChanged();
}

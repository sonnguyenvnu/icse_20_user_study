@Override public void openProfile(Account account){
  AccountUserResource userResource=mUserResourceMap.get(account);
  Intent intent;
  if (userResource.has()) {
    intent=ProfileActivity.makeIntent((SimpleUser)userResource.get(),getActivity());
  }
 else {
    intent=ProfileActivity.makeIntent(userResource.getUserIdOrUid(),getActivity());
  }
  startActivity(intent);
}

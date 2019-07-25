private void init(){
  conversationViewModel=ViewModelProviders.of(this,new ConversationViewModelFactory(conversationInfo.conversation)).get(ConversationViewModel.class);
  userViewModel=WfcUIKit.getAppScopeViewModel(UserViewModel.class);
  ContactViewModel contactViewModel=ViewModelProviders.of(this).get(ContactViewModel.class);
  String userId=userViewModel.getUserId();
  conversationMemberAdapter=new ConversationMemberAdapter(true,false);
  List<UserInfo> members=Collections.singletonList(userViewModel.getUserInfo(userId,false));
  conversationMemberAdapter.setMembers(members);
  conversationMemberAdapter.setOnMemberClickListener(this);
  memberReclerView.setAdapter(conversationMemberAdapter);
  memberReclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
  stickTopSwitchButton.setChecked(conversationInfo.isTop);
  silentSwitchButton.setChecked(conversationInfo.isSilent);
  stickTopSwitchButton.setOnCheckedChangeListener(this);
  silentSwitchButton.setOnCheckedChangeListener(this);
}

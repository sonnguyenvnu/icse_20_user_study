private void init(){
  ConversationListViewModel conversationListViewModel=ViewModelProviders.of(this,new ConversationListViewModelFactory(Arrays.asList(Conversation.ConversationType.Single,Conversation.ConversationType.Group),Arrays.asList(0))).get(ConversationListViewModel.class);
  ForwardAdapter adapter=new ForwardAdapter(this);
  recyclerView.setAdapter(adapter);
  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
  List<Conversation.ConversationType> types=Arrays.asList(Conversation.ConversationType.Single,Conversation.ConversationType.Group);
  List<Integer> liens=Arrays.asList(0);
  List<ConversationInfo> conversationInfos=conversationListViewModel.getConversationList(types,liens);
  adapter.setConversations(conversationInfos);
  adapter.setOnConversationItemClickListener(this);
  adapter.setNewConversationItemClickListener(this);
  adapter.notifyDataSetChanged();
}

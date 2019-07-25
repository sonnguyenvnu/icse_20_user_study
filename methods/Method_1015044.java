@OnClick(R.id.chatButton) void chat(){
  Intent intent=new Intent(getActivity(),ConversationActivity.class);
  Conversation conversation=new Conversation(Conversation.ConversationType.Single,userInfo.uid,0);
  intent.putExtra("conversation",conversation);
  startActivity(intent);
  getActivity().finish();
}

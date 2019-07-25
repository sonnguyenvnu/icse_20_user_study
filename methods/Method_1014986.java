@OnClick(R.id.robotOptionItemView) void robot(){
  Intent intent=ConversationActivity.buildConversationIntent(getActivity(),Conversation.ConversationType.Single,"FireRobot",0);
  startActivity(intent);
}

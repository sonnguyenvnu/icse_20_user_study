@OnClick(R.id.contentTextView) public void call(View view){
  if (((CallStartMessageContent)message.message.content).getStatus() == 1) {
    return;
  }
  WfcUIKit.onCall(context,message.message.conversation.target,true,false);
}

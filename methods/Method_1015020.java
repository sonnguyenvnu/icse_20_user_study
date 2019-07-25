@OnClick(R.id.imageView) void preview(){
  List<UiMessage> messages=((ConversationMessageAdapter)adapter).getMessages();
  List<UiMessage> mmMessages=new ArrayList<>();
  for (  UiMessage msg : messages) {
    if (msg.message.content.getType() == cn.wildfirechat.message.core.MessageContentType.ContentType_Image || msg.message.content.getType() == cn.wildfirechat.message.core.MessageContentType.ContentType_Video) {
      mmMessages.add(msg);
    }
  }
  if (mmMessages.isEmpty()) {
    return;
  }
  int current=0;
  for (int i=0; i < mmMessages.size(); i++) {
    if (message.message.messageId == mmMessages.get(i).message.messageId) {
      current=i;
      break;
    }
  }
  MMPreviewActivity.startActivity(context,mmMessages,current);
}

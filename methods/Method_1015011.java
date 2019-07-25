/** 
 * @param containerView ??view?container
 * @param conversation
 */
@ExtContextMenuItem(title="??") public void shoot(View containerView,Conversation conversation){
  Intent intent=new Intent(context,TakePhotoActivity.class);
  startActivityForResult(intent,100);
  TypingMessageContent content=new TypingMessageContent(TypingMessageContent.TYPING_CAMERA);
  conversationViewModel.sendMessage(content);
}

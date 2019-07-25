@MessageContextMenuItem(tag=MessageContextMenuItemTags.TAG_CLIP,title="??",confirm=false,priority=12) public void clip(View itemView,UiMessage message){
  ClipboardManager clipboardManager=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
  if (clipboardManager == null) {
    return;
  }
  TextMessageContent content=(TextMessageContent)message.message.content;
  ClipData clipData=ClipData.newPlainText("messageContent",content.getContent());
  clipboardManager.setPrimaryClip(clipData);
}

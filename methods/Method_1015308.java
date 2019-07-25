private void send(){
  String msg=String.format("%s(Reviews: %s)",text.getText(),styledText.getText());
  for (  IMChatConsole console : consoles) {
    String name=console instanceof MockChatConsole ? "me" : console.getClient().getAccount().getName();
    String msg2=WXUtils.formatHtmlOutgoing(name,IMUtils.autoReviewLink(msg),false);
    console.sendWithoutPost(msg2,true);
    console.post(msg);
  }
  if (openGerritReviewCheckBox.isSelected()) {
    AnAction action=ActionManager.getInstance().getActionOrStub("");
  }
}

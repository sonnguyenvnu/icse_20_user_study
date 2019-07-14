@Override public void handleTagNode(TagNode node,SpannableStringBuilder builder,int start,int end){
  String emoji=node.getAttributeByName("alias");
  if (emoji != null) {
    Emoji unicode=EmojiManager.getForAlias(emoji);
    if (unicode != null && unicode.getUnicode() != null) {
      builder.replace(start,end," " + unicode.getUnicode() + " ");
    }
  }
 else   if (node.getText() != null) {
    Logger.e(node.getText());
    Emoji unicode=EmojiManager.getForAlias(node.getText().toString());
    if (unicode != null && unicode.getUnicode() != null) {
      builder.replace(start,end," " + unicode.getUnicode() + " ");
    }
  }
}

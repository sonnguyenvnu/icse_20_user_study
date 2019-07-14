@Override public void handleTagNode(TagNode node,SpannableStringBuilder spannableStringBuilder,int start,int end){
  String href=node.getAttributeByName("href");
  if (href != null) {
    spannableStringBuilder.setSpan(new LinkSpan(href,linkColor),start,end,33);
  }
 else   if (node.getText() != null) {
    spannableStringBuilder.setSpan(new LinkSpan("https://github.com/" + node.getText().toString(),linkColor),start,end,33);
  }
}

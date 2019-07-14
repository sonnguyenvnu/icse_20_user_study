static Component.Builder createMessageContent(ComponentContext c,String messageText){
  return Row.create(c).paddingDip(YogaEdge.ALL,8).marginDip(YogaEdge.ALL,8).background(ExpandableElementUtil.getMessageBackground(c,0xFF0084FF)).child(Text.create(c).textSizeDip(18).textColor(Color.WHITE).text(messageText));
}

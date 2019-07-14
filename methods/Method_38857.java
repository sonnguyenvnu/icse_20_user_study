@Override public void text(final CharSequence text){
  if (!enabled) {
    return;
  }
  String textValue=text.toString();
  Node node=new Text(rootNode,textValue);
  parentNode.addChild(node);
}

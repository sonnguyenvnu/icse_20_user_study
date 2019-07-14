@Override public void script(final Tag tag,final CharSequence body){
  if (!enabled) {
    return;
  }
  Element node=createElementNode(tag);
  parentNode.addChild(node);
  if (body.length() != 0) {
    Node text=new Text(rootNode,body.toString());
    node.addChild(text);
  }
}

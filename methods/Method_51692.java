private void init(){
  StringBuffer buf=new StringBuffer(200);
  for (Node temp=node; temp != null; temp=temp.jjtGetParent()) {
    buf.insert(0,"/" + temp.toString());
  }
  add(new XPathFragmentAddingItem(NLS.nls("AST.MENU.NODE.ADD_ABSOLUTE_PATH"),model,buf.toString()));
  add(new XPathFragmentAddingItem(NLS.nls("AST.MENU.NODE.ADD_ALLDESCENDANTS"),model,"//" + node.toString()));
}

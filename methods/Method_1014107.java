@Override ASTNode parse(ResourceBundle language,TokenList tokenList){
  TokenList list=tokenList;
  ASTNode node=new ASTNode(), cr;
  ArrayList<ASTNode> nodes=new ArrayList<ASTNode>();
  ArrayList<Object> values=new ArrayList<Object>();
  while ((cr=subExpression.parse(language,list)).isSuccess()) {
    nodes.add(cr);
    values.add(cr.getValue());
    list=cr.getRemainingTokens();
    if (atMostOne) {
      break;
    }
  }
  if (!(atLeastOne && nodes.size() == 0)) {
    node.setChildren(nodes.toArray(new ASTNode[0]));
    node.setRemainingTokens(list);
    node.setSuccess(true);
    node.setValue(atMostOne ? (values.size() > 0 ? values.get(0) : null) : values.toArray());
    generateValue(node);
  }
  return node;
}

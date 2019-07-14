private String getIdentifyName(AbstractVmNode node){
  Token token=node.getFirstToken();
  StringBuilder sb=new StringBuilder();
  while (token.kind >= VmParserConstants.IDENTIFIER && token.kind < VmParserConstants.RCURLY) {
    if (token.kind != VmParserConstants.LCURLY) {
      sb.append(token.image);
    }
    token=token.next;
  }
  return sb.toString();
}

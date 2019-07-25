@Override public void tokenize(@NotNull MultiMarkdownNamedElement element,TokenConsumer consumer){
  StringBuilder text=new StringBuilder(element.getTextLength());
  int offset=-1;
  int nodeOffset=-1;
  int firstOffset=-1;
  int lastOffset=-1;
  for (  ASTNode astNode : element.getNode().getChildren(null)) {
    if (!MultiMarkdownSpellcheckingStrategy.NO_SPELL_CHECK_SET.contains(astNode.getElementType())) {
      offset=astNode.getStartOffset();
      if (firstOffset < 0)       firstOffset=offset;
 else       if (lastOffset < offset)       appendSpaces(text,offset - lastOffset);
      text.append(astNode.getChars());
      lastOffset=offset + astNode.getTextLength();
    }
    if (nodeOffset < 0) {
      nodeOffset=offset >= 0 ? offset : astNode.getStartOffset();
    }
  }
  if (nodeOffset < 0) {
    String elemText=element.getText();
    consumer.consumeToken(element,elemText,true,0,TextRange.allOf(elemText),IdentifierSplitter.getInstance());
  }
 else   if (firstOffset >= 0) {
    consumer.consumeToken(element,text.toString(),true,firstOffset - element.getNode().getStartOffset(),TextRange.create(0,lastOffset - firstOffset),IdentifierSplitter.getInstance());
  }
}

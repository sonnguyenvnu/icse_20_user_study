private void init(@NonNull MarkdownConfiguration markdownConfiguration){
  mMarkdownConfiguration=markdownConfiguration;
  mTotalChain=new MultiSyntaxChain(getCodeBlockSyntax(markdownConfiguration),getListSyntax(markdownConfiguration));
  mLineChain=new SyntaxChain(getHorizontalRulesSyntax(markdownConfiguration));
  SyntaxDoElseChain blockQuitesChain=new SyntaxDoElseChain(getBlockQuotesSyntax(markdownConfiguration));
  SyntaxDoElseChain todoChain=new SyntaxDoElseChain(getTodoSyntax(markdownConfiguration));
  SyntaxDoElseChain todoDoneChain=new SyntaxDoElseChain(getTodoDoneSyntax(markdownConfiguration));
  SyntaxMultiChains centerAlignChain=new SyntaxMultiChains(getCenterAlignSyntax(markdownConfiguration));
  SyntaxMultiChains headerChain=new SyntaxMultiChains(getHeaderSyntax(markdownConfiguration));
  MultiSyntaxChain multiChain=new MultiSyntaxChain(getImageSyntax(markdownConfiguration),getHyperLinkSyntax(markdownConfiguration),getCodeSyntax(markdownConfiguration),getBoldSyntax(markdownConfiguration),getItalicSyntax(markdownConfiguration),getStrikeThroughSyntax(markdownConfiguration),getFootnoteSyntax(markdownConfiguration));
  SyntaxChain backslashChain=new SyntaxChain(getBackslashSyntax(markdownConfiguration));
  mLineChain.setNextHandleSyntax(blockQuitesChain);
  blockQuitesChain.setNextHandleSyntax(todoChain);
  blockQuitesChain.addNextHandleSyntax(multiChain);
  todoChain.setNextHandleSyntax(todoDoneChain);
  todoChain.addNextHandleSyntax(multiChain);
  todoDoneChain.setNextHandleSyntax(centerAlignChain);
  todoDoneChain.addNextHandleSyntax(multiChain);
  centerAlignChain.addNextHandleSyntax(headerChain);
  centerAlignChain.addNextHandleSyntax(multiChain);
  multiChain.setNextHandleSyntax(backslashChain);
}

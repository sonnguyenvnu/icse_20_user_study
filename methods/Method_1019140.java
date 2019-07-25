private void init(MarkdownConfiguration markdownConfiguration){
  mMarkdownConfiguration=markdownConfiguration;
  mSyntaxList=new ArrayList<>();
  mSyntaxList.add(getBoldSyntax(mMarkdownConfiguration));
  mSyntaxList.add(getItalicSyntax(mMarkdownConfiguration));
  mSyntaxList.add(getStrikeThroughSyntax(mMarkdownConfiguration));
  mSyntaxList.add(getCodeSyntax(mMarkdownConfiguration));
  mSyntaxList.add(getCenterAlignSyntax(mMarkdownConfiguration));
  mSyntaxList.add(getHeaderSyntax(mMarkdownConfiguration));
  mSyntaxList.add(getBlockQuotesSyntax(mMarkdownConfiguration));
  mSyntaxList.add(getCodeBlockSyntax(mMarkdownConfiguration));
  mSyntaxList.add(getHorizontalRulesSyntax(mMarkdownConfiguration));
  mSyntaxList.add(getOrderListSyntax(mMarkdownConfiguration));
  mSyntaxList.add(getUnOrderListSyntax(mMarkdownConfiguration));
}

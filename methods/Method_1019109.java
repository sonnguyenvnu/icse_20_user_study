private void markdown(HorizontalEditScrollView horizontalEditScrollView){
  MarkdownConfiguration markdownConfiguration=new MarkdownConfiguration.Builder(this).setDefaultImageSize(400,400).setBlockQuotesLineColor(0xff33b5e5).setHeader1RelativeSize(1.6f).setHeader2RelativeSize(1.5f).setHeader3RelativeSize(1.4f).setHeader4RelativeSize(1.3f).setHeader5RelativeSize(1.2f).setHeader6RelativeSize(1.1f).setHorizontalRulesColor(0xffaa66cc).setCodeBgColor(0x33CCCCCC).setTodoColor(0xff669900).setTodoDoneColor(0xffff4444).setUnOrderListColor(0xffffbb33).setRxMDImageLoader(new OKLoader(this)).showLinkUnderline(true).setLinkFontColor(0xff00ddff).build();
  horizontalEditScrollView.setEditTextAndConfig(mMarkdownEditText,markdownConfiguration);
  mMarkdownProcessor=new MarkdownProcessor(this);
  mMarkdownProcessor.config(markdownConfiguration);
  mMarkdownProcessor.factory(EditFactory.create());
  mMarkdownProcessor.live(mMarkdownEditText);
}

private void markdown(){
  MarkdownConfiguration markdownConfiguration=new MarkdownConfiguration.Builder(this).setDefaultImageSize(50,50).setBlockQuotesLineColor(0xff33b5e5).setHeader1RelativeSize(1.6f).setHeader2RelativeSize(1.5f).setHeader3RelativeSize(1.4f).setHeader4RelativeSize(1.3f).setHeader5RelativeSize(1.2f).setHeader6RelativeSize(1.1f).setHorizontalRulesColor(0xff99cc00).setCodeBgColor(0xffff4444).setTodoColor(0xffaa66cc).setTodoDoneColor(0xffff8800).setUnOrderListColor(0xff00ddff).build();
  mHorizontalEditScrollView.setEditTextAndConfig(mMarkdownEditText,markdownConfiguration);
  mMarkdownEditText.setText(Const.MD_SAMPLE);
  mMarkdownProcessor=new MarkdownProcessor(this);
  mMarkdownProcessor.config(markdownConfiguration);
  mMarkdownProcessor.factory(EditFactory.create());
  mMarkdownProcessor.live(mMarkdownEditText);
}

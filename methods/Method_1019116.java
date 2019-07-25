private void markdown(final TextView textView,String content,MDImageLoader imageLoader){
  MarkdownConfiguration markdownConfiguration=new MarkdownConfiguration.Builder(this).setDefaultImageSize(50,50).setBlockQuotesLineColor(0xff33b5e5).setHeader1RelativeSize(1.6f).setHeader2RelativeSize(1.5f).setHeader3RelativeSize(1.4f).setHeader4RelativeSize(1.3f).setHeader5RelativeSize(1.2f).setHeader6RelativeSize(1.1f).setHorizontalRulesColor(0xff99cc00).setCodeBgColor(0xffff4444).setTodoColor(0xffaa66cc).setTodoDoneColor(0xffff8800).setUnOrderListColor(0xff00ddff).setRxMDImageLoader(imageLoader).setHorizontalRulesHeight(1).setLinkFontColor(Color.BLUE).showLinkUnderline(false).setTheme(new ThemeSunburst()).setOnLinkClickCallback(new OnLinkClickCallback(){
    @Override public void onLinkClicked(    View view,    String link){
      toast(link);
    }
  }
).setOnTodoClickCallback(new OnTodoClickCallback(){
    @Override public CharSequence onTodoClicked(    View view,    String line,    int lineNumber){
      toast("line:" + line + "\n" + "line number:" + lineNumber);
      return textView.getText();
    }
  }
).build();
  MarkdownProcessor processor=new MarkdownProcessor(this);
  processor.factory(TextFactory.create());
  processor.config(markdownConfiguration);
  textView.setText(processor.parse(content));
}

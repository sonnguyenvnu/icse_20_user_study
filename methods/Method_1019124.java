private void prepare(MarkdownEditText MarkdownEditText,MarkdownEditText.EditTextWatcher editTextWatcher){
  mEditControllerList=new ArrayList<>();
  mEditControllerList.add(new BlockQuotesLive());
  mEditControllerList.add(new StyleLive());
  mEditControllerList.add(new CenterAlignLive());
  mEditControllerList.add(new HeaderLive());
  mEditControllerList.add(new HorizontalRulesLive(MarkdownEditText));
  mEditControllerList.add(new CodeLive());
  mEditControllerList.add(new StrikeThroughLive());
  mEditControllerList.add(new ListLive(MarkdownEditText,editTextWatcher));
  mEditControllerList.add(new CodeBlockLive());
}

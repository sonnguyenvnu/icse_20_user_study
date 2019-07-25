private CharSequence format(){
  if (mGrammarFactory == null) {
    return getText();
  }
  Editable editable=getText();
  long begin=System.currentTimeMillis();
  CharSequence charSequence=mGrammarFactory.parse(editable,mMarkdownConfiguration);
  return charSequence;
}

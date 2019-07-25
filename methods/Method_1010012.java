private void initialize(Context context){
  int inputType=(getInputType() | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT) & ~InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
  setInputType(inputType);
  mMentionAdapter=new DropDownUserAdapter(context);
  setAdapter(mMentionAdapter);
  setTokenizer(new UiUtils.WhitespaceTokenizer());
  setThreshold(1);
  updateLockState();
  setOnEditorActionListener(this);
}

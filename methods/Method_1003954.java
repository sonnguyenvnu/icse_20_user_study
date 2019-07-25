@Override public void reset(CharSequence input){
  if (input instanceof TokenizedCharSequence) {
    clearAttributes();
    tokenized=(TokenizedCharSequence)input;
    currentIndex=0;
    updateInputCharSequence(tokenized);
  }
 else {
    super.reset(input);
    tokenized=null;
  }
}

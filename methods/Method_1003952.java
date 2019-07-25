@Override public void reset(){
  CharSequence input=inputCharSequence();
  Preconditions.checkNotNull(input);
  try {
    tokenizer.setReader(new StringReader(input.toString()));
    updateInputCharSequence(input);
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}

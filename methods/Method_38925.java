protected void consumeCharacterReference(){
  ndx++;
  if (isEOF()) {
    return;
  }
  _consumeCharacterReference();
}

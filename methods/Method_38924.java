protected void consumeCharacterReference(final char allowedChar){
  ndx++;
  if (isEOF()) {
    return;
  }
  char c=input[ndx];
  if (c == allowedChar) {
    ndx--;
    return;
  }
  _consumeAttrCharacterReference();
}

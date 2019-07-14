private void _consumeCharacterReference(){
  int unconsumeNdx=ndx - 1;
  char c=input[ndx];
  if (equalsOne(c,CONTINUE_CHARS)) {
    ndx=unconsumeNdx;
    textEmitChar('&');
    return;
  }
  if (c == '#') {
    _consumeNumber(unconsumeNdx);
  }
 else {
    String name=HtmlDecoder.detectName(input,ndx);
    if (name == null) {
      errorCharReference();
      textEmitChar('&');
      ndx=unconsumeNdx;
      return;
    }
    ndx+=name.length();
    textEmitChars(HtmlDecoder.lookup(name));
    c=input[ndx];
    if (c != ';') {
      errorCharReference();
      ndx--;
    }
  }
}

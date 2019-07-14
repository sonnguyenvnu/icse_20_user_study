private void _consumeAttrCharacterReference(){
  final int unconsumeNdx=ndx - 1;
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
    final String name=HtmlDecoder.detectName(input,ndx);
    if (name == null) {
      errorCharReference();
      textEmitChar('&');
      ndx=unconsumeNdx;
      return;
    }
    ndx+=name.length();
    c=input[ndx];
    if (c == ';') {
      textEmitChars(HtmlDecoder.lookup(name));
    }
 else {
      textEmitChar('&');
      ndx=unconsumeNdx;
    }
  }
}

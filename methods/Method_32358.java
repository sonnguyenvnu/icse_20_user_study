long doParseMillis(InternalParser parser,CharSequence text){
  int newPos=parser.parseInto(this,text,0);
  if (newPos >= 0) {
    if (newPos >= text.length()) {
      return computeMillis(true,text);
    }
  }
 else {
    newPos=~newPos;
  }
  throw new IllegalArgumentException(FormatUtils.createErrorMessage(text.toString(),newPos));
}

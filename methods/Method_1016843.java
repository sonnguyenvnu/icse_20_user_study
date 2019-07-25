private TokenSequence stem(TokenSequence tokenSequence){
  PorterStemmer stemmer=new PorterStemmer();
  StringIterator text=new StringIterator("");
  Iterator<Token> tokens=tokenSequence.iterator();
  while (tokens.hasNext()) {
    Token token=tokens.next();
    if (token == null || !(token instanceof StringSpan)) {
      continue;
    }
    text.reset(token.getText());
    while (!text.isEndOfText()) {
      char c=text.peek();
      if (!Character.isLetter(c)) {
        break;
      }
      stemmer.add(c);
      text.moveAhead();
    }
    stemmer.stem();
    token.setText(stemmer.toString());
  }
  return tokenSequence;
}

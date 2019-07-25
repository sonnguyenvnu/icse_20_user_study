private List<Token> tokenize() throws LexerException {
  List<Token> tokens=new ArrayList<>();
  do {
    skipWhitespace();
    if (position >= expression.length()) {
      break;
    }
    Token token=operatorTree.evaluate(position);
    if (token != null) {
      tokens.add(token);
      continue;
    }
    final char ch=peek();
    if (characterTokens.contains(ch)) {
      tokens.add(new CharacterToken(position++,ch));
      continue;
    }
    final Matcher numberMatcher=numberPattern.matcher(expression.substring(position));
    if (numberMatcher.lookingAt()) {
      String numberPart=numberMatcher.group(1);
      if (!numberPart.isEmpty()) {
        try {
          tokens.add(new NumberToken(position,Double.parseDouble(numberPart)));
        }
 catch (        NumberFormatException e) {
          throw new LexerException(position,"Number parsing failed",e);
        }
        position+=numberPart.length();
        continue;
      }
    }
    final Matcher identifierMatcher=identifierPattern.matcher(expression.substring(position));
    if (identifierMatcher.lookingAt()) {
      String identifierPart=identifierMatcher.group(1);
      if (!identifierPart.isEmpty()) {
        if (keywords.contains(identifierPart)) {
          tokens.add(new KeywordToken(position,identifierPart));
        }
 else {
          tokens.add(new IdentifierToken(position,identifierPart));
        }
        position+=identifierPart.length();
        continue;
      }
    }
    throw new LexerException(position,"Unknown character '" + ch + "'");
  }
 while (position < expression.length());
  return tokens;
}

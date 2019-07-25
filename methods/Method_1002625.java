public static int collect(final Signal signal,final List<Token> tokens,final int index,final List<Token> collected){
  int i=index;
  while (i < tokens.size()) {
    final Token token=tokens.get(i);
    if (signal != token.signal()) {
      break;
    }
    final int tokenCount=token.componentTokenCount();
    for (final int limit=i + tokenCount; i < limit; i++) {
      collected.add(tokens.get(i));
    }
  }
  return i;
}

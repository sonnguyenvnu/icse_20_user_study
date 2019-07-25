public void advance(){
  if (eexLexer.getTokenType() == ELIXIR) {
    elixirLexer.advance();
    if (elixirLexer.getTokenType() == null) {
      eexLexer.advance();
    }
  }
 else {
    eexLexer.advance();
    if (eexLexer.getTokenType() == ELIXIR) {
      elixirLexer.start(getBufferSequence(),eexLexer.getTokenStart(),eexLexer.getTokenEnd());
    }
  }
}

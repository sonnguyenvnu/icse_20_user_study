public void push(String quotePromoter,int currentLexicalState){
  org.elixir_lang.lexer.group.Quote quote=org.elixir_lang.lexer.group.Quote.fetch(quotePromoter);
  push(quote,quotePromoter,currentLexicalState);
}

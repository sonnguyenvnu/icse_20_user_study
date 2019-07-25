@Contract(pure=true) @NotNull private Lexer lexer(){
  return forLanguage(eexLexer,eexLexer,elixirLexer);
}

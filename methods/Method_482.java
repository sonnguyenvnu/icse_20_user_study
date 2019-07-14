protected Enum<?> scanEnum(JSONLexer lexer,char seperator){
  throw new JSONException("illegal enum. " + lexer.info());
}

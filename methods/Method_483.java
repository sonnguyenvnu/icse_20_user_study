protected Enum scanEnum(JSONLexerBase lexer,char[] name_chars,ObjectDeserializer fieldValueDeserilizer){
  EnumDeserializer enumDeserializer=null;
  if (fieldValueDeserilizer instanceof EnumDeserializer) {
    enumDeserializer=(EnumDeserializer)fieldValueDeserilizer;
  }
  if (enumDeserializer == null) {
    lexer.matchStat=JSONLexer.NOT_MATCH;
    return null;
  }
  long enumNameHashCode=lexer.scanEnumSymbol(name_chars);
  if (lexer.matchStat > 0) {
    Enum e=enumDeserializer.getEnumByHashCode(enumNameHashCode);
    if (e == null) {
      if (enumNameHashCode == 0xcbf29ce484222325L) {
        return null;
      }
      if (lexer.isEnabled(Feature.ErrorOnEnumNotMatch)) {
        throw new JSONException("not match enum value, " + enumDeserializer.enumClass);
      }
    }
    return e;
  }
 else {
    return null;
  }
}

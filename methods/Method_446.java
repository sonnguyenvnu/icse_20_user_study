@SuppressWarnings({"unchecked","rawtypes"}) public final void parseArray(final Collection array,Object fieldName){
  final JSONLexer lexer=this.lexer;
  if (lexer.token() == JSONToken.SET || lexer.token() == JSONToken.TREE_SET) {
    lexer.nextToken();
  }
  if (lexer.token() != JSONToken.LBRACKET) {
    throw new JSONException("syntax error, expect [, actual " + JSONToken.name(lexer.token()) + ", pos " + lexer.pos() + ", fieldName " + fieldName);
  }
  lexer.nextToken(JSONToken.LITERAL_STRING);
  ParseContext context=this.context;
  this.setContext(array,fieldName);
  try {
    for (int i=0; ; ++i) {
      if (lexer.isEnabled(Feature.AllowArbitraryCommas)) {
        while (lexer.token() == JSONToken.COMMA) {
          lexer.nextToken();
          continue;
        }
      }
      Object value;
switch (lexer.token()) {
case LITERAL_INT:
        value=lexer.integerValue();
      lexer.nextToken(JSONToken.COMMA);
    break;
case LITERAL_FLOAT:
  if (lexer.isEnabled(Feature.UseBigDecimal)) {
    value=lexer.decimalValue(true);
  }
 else {
    value=lexer.decimalValue(false);
  }
lexer.nextToken(JSONToken.COMMA);
break;
case LITERAL_STRING:
String stringLiteral=lexer.stringVal();
lexer.nextToken(JSONToken.COMMA);
if (lexer.isEnabled(Feature.AllowISO8601DateFormat)) {
JSONScanner iso8601Lexer=new JSONScanner(stringLiteral);
if (iso8601Lexer.scanISO8601DateIfMatch()) {
value=iso8601Lexer.getCalendar().getTime();
}
 else {
value=stringLiteral;
}
iso8601Lexer.close();
}
 else {
value=stringLiteral;
}
break;
case TRUE:
value=Boolean.TRUE;
lexer.nextToken(JSONToken.COMMA);
break;
case FALSE:
value=Boolean.FALSE;
lexer.nextToken(JSONToken.COMMA);
break;
case LBRACE:
JSONObject object=new JSONObject(lexer.isEnabled(Feature.OrderedField));
value=parseObject(object,i);
break;
case LBRACKET:
Collection items=new JSONArray();
parseArray(items,i);
if (lexer.isEnabled(Feature.UseObjectArray)) {
value=items.toArray();
}
 else {
value=items;
}
break;
case NULL:
value=null;
lexer.nextToken(JSONToken.LITERAL_STRING);
break;
case UNDEFINED:
value=null;
lexer.nextToken(JSONToken.LITERAL_STRING);
break;
case RBRACKET:
lexer.nextToken(JSONToken.COMMA);
return;
case EOF:
throw new JSONException("unclosed jsonArray");
default :
value=parse();
break;
}
array.add(value);
checkListResolve(array);
if (lexer.token() == JSONToken.COMMA) {
lexer.nextToken(JSONToken.LITERAL_STRING);
continue;
}
}
}
  finally {
this.setContext(context);
}
}

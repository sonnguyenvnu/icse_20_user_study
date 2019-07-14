public boolean hasNext(){
  if (context == null) {
    throw new JSONException("context is null");
  }
  final int token=parser.lexer.token();
  final int state=context.state;
switch (state) {
case StartArray:
case ArrayValue:
    return token != JSONToken.RBRACKET;
case StartObject:
case PropertyValue:
  return token != JSONToken.RBRACE;
default :
throw new JSONException("illegal state : " + state);
}
}

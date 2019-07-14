private void readBefore(){
  int state=context.state;
switch (state) {
case PropertyKey:
    parser.accept(JSONToken.COLON);
  break;
case PropertyValue:
parser.accept(JSONToken.COMMA,JSONToken.IDENTIFIER);
break;
case ArrayValue:
parser.accept(JSONToken.COMMA);
break;
case StartObject:
break;
case StartArray:
break;
default :
throw new JSONException("illegal state : " + state);
}
}

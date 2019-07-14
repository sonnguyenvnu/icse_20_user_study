private void startStructure(){
  final int state=context.state;
switch (state) {
case PropertyKey:
    parser.accept(JSONToken.COLON);
  break;
case PropertyValue:
case ArrayValue:
parser.accept(JSONToken.COMMA);
break;
case StartArray:
case StartObject:
break;
default :
throw new JSONException("illegal state : " + context.state);
}
}

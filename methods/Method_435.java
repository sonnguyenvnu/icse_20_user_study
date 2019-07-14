private void beginStructure(){
  final int state=context.state;
switch (context.state) {
case PropertyKey:
    writer.write(':');
  break;
case ArrayValue:
writer.write(',');
break;
case StartObject:
break;
case StartArray:
break;
default :
throw new JSONException("illegal state : " + state);
}
}

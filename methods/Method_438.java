private void afterWriter(){
  if (context == null) {
    return;
  }
  final int state=context.state;
  int newState=-1;
switch (state) {
case PropertyKey:
    newState=PropertyValue;
  break;
case StartObject:
case PropertyValue:
newState=PropertyKey;
break;
case StartArray:
newState=ArrayValue;
break;
case ArrayValue:
break;
default :
break;
}
if (newState != -1) {
context.state=newState;
}
}

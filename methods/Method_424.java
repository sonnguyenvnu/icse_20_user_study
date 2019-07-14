private void endStructure(){
  context=context.parent;
  if (context == null) {
    return;
  }
  final int state=context.state;
  int newState=-1;
switch (state) {
case PropertyKey:
    newState=PropertyValue;
  break;
case StartArray:
newState=ArrayValue;
break;
case PropertyValue:
case StartObject:
newState=PropertyKey;
break;
default :
break;
}
if (newState != -1) {
context.state=newState;
}
}

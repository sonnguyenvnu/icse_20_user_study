private void endStructure(){
  context=context.parent;
  if (context == null) {
    return;
  }
  int newState=-1;
switch (context.state) {
case PropertyKey:
    newState=PropertyValue;
  break;
case StartArray:
newState=ArrayValue;
break;
case ArrayValue:
break;
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

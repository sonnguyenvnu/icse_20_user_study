private void beforeWrite(){
  if (context == null) {
    return;
  }
switch (context.state) {
case StartObject:
case StartArray:
    break;
case PropertyKey:
  writer.write(':');
break;
case PropertyValue:
writer.write(',');
break;
case ArrayValue:
writer.write(',');
break;
default :
break;
}
}

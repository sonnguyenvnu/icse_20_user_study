private void readAfter(){
  int state=context.state;
  int newStat=-1;
switch (state) {
case StartObject:
    newStat=PropertyKey;
  break;
case PropertyKey:
newStat=PropertyValue;
break;
case PropertyValue:
newStat=PropertyKey;
break;
case ArrayValue:
break;
case StartArray:
newStat=ArrayValue;
break;
default :
throw new JSONException("illegal state : " + state);
}
if (newStat != -1) {
context.state=newStat;
}
}

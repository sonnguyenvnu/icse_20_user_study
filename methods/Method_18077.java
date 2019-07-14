public void setPadding(YogaEdge edge,YogaValue value){
switch (value.unit) {
case UNDEFINED:
case AUTO:
    mNode.paddingPx(edge,0);
  break;
case PERCENT:
mNode.paddingPercent(edge,value.value);
break;
case POINT:
mNode.paddingPx(edge,(int)value.value);
break;
}
}

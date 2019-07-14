public void setFlexBasis(YogaValue value){
switch (value.unit) {
case UNDEFINED:
case AUTO:
    mNode.flexBasisAuto();
  break;
case PERCENT:
mNode.flexBasisPercent(value.value);
break;
case POINT:
mNode.flexBasisPx((int)value.value);
break;
}
}

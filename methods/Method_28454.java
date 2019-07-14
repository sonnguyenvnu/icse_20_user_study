private void updateTopPositionByGravity(ViewPosition pos,int size,int gravity){
switch (gravity & Gravity.VERTICAL_GRAVITY_MASK) {
case Gravity.BOTTOM:
    pos.top+=(size > 0) ? size : 0;
  break;
case Gravity.CENTER_VERTICAL:
pos.top+=((size > 0) ? size : 0) / 2;
break;
}
}

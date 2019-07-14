private void updateLeftPositionByGravity(ViewPosition pos,int size,int gravity){
switch (gravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
case GravityCompat.END:
    pos.left+=(size > 0) ? size : 0;
  break;
case Gravity.CENTER_HORIZONTAL:
pos.left+=((size > 0) ? size : 0) / 2;
break;
}
}

private static int getRotation(Side pos){
switch (pos) {
case TOP:
    return 0;
case BOTTOM:
  return 180;
case LEFT:
return -90;
case RIGHT:
return 90;
default :
return 0;
}
}

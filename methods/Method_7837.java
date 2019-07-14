private int getTextColor(){
switch (getSelectedColor()) {
case 0:
case 1:
    return 0xff212121;
case 2:
default :
  return 0xff999999;
}
}

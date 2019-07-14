public static Direction fromPosition(int pos){
switch (pos) {
case 0:
    return Direction.OUT;
case 1:
  return Direction.IN;
default :
throw new IllegalArgumentException("Invalid position:" + pos);
}
}

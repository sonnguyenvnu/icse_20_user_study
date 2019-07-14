public static int position(Direction dir){
switch (dir) {
case OUT:
    return 0;
case IN:
  return 1;
default :
throw new IllegalArgumentException("Invalid direction: " + dir);
}
}

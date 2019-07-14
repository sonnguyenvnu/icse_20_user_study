public static boolean decode(byte b){
switch (b) {
case 0:
    return false;
case 1:
  return true;
default :
throw new IllegalArgumentException("Invalid boolean value: " + b);
}
}

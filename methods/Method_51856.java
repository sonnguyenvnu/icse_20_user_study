private int getIntBase(){
  final String image=getImage().toLowerCase(Locale.ROOT);
  final int offset=image.charAt(0) == '-' ? 1 : 0;
  if (image.startsWith("0x",offset)) {
    return 16;
  }
  if (image.startsWith("0b",offset)) {
    return 2;
  }
  if (image.startsWith("0",offset) && image.length() > 1) {
    return 8;
  }
  return 10;
}

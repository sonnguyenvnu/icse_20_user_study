private static int toEven(float value){
  final int i=(int)(value + .5f);
  if (i % 2 == 1) {
    return i - 1;
  }
  return i;
}

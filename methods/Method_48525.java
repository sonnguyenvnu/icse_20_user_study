public static int positiveLength(long value){
  assert value >= 0;
  return unsignedNumBlocks(value);
}

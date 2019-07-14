public static void writePositiveBackward(WriteBuffer out,long value){
  assert value >= 0;
  writeUnsignedBackward(out,value);
}

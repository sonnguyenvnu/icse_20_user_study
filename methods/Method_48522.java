public static void writePositive(WriteBuffer out,final long value){
  assert value >= 0;
  writeUnsigned(out,value);
}

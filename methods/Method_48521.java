public static long readPositive(ScanBuffer in){
  long value=readUnsigned(in);
  assert value >= 0;
  return value;
}

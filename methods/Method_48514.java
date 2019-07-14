public static byte unsignedByte(int value){
  Preconditions.checkArgument(value >= 0 && value < 256,"Value overflow: %s",value);
  return (byte)(value & 0xFF);
}

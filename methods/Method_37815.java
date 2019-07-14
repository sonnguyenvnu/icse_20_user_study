public static boolean isSet(final byte value,final byte mask){
  return (value & mask) == mask;
}

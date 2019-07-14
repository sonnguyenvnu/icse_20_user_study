public static final byte[] toByteArray(final int value){
  return SafeEncoder.encode(String.valueOf(value));
}

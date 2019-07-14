public static String toNonceString(long value){
  return String.format("%06d",Math.abs(value) % 100000);
}

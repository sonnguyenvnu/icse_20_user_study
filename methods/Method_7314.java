public static boolean isGoodGaAndGb(BigInteger g_a,BigInteger p){
  return !(g_a.compareTo(BigInteger.valueOf(1)) <= 0 || g_a.compareTo(p.subtract(BigInteger.valueOf(1))) >= 0);
}

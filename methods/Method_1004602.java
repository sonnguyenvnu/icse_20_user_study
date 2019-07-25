private static double middle(final double pA,final double pB,final double pC,final double pD){
  return (Math.min(Math.max(pA,pB),Math.max(pC,pD)) + Math.max(Math.min(pA,pB),Math.min(pC,pD))) / 2;
}

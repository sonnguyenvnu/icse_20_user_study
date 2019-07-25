/** 
 * Checks if computed intersection is valid and sets output accordingly
 * @param pXI intersection x
 * @param pYI intersection y
 * @return true if OK
 */
private static boolean check(final double pXA,final double pYA,final double pXB,final double pYB,final double pXC,final double pYC,final double pXD,final double pYD,final PointL pIntersection,final double pXI,final double pYI){
  if (pXI < Math.min(pXA,pXB) || pXI > Math.max(pXA,pXB)) {
    return false;
  }
  if (pXI < Math.min(pXC,pXD) || pXI > Math.max(pXC,pXD)) {
    return false;
  }
  if (pYI < Math.min(pYA,pYB) || pYI > Math.max(pYA,pYB)) {
    return false;
  }
  if (pYI < Math.min(pYC,pYD) || pYI > Math.max(pYC,pYD)) {
    return false;
  }
  if (pIntersection != null) {
    pIntersection.x=Math.round(pXI);
    pIntersection.y=Math.round(pYI);
  }
  return true;
}

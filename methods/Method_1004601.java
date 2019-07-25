/** 
 * @param pXA           Starting point of Segment 1 [AB]
 * @param pYA           Starting point of Segment 1
 * @param pXB           Ending point of Segment 1
 * @param pYB           Ending point of Segment 1
 * @param pXC           Starting point of Segment 2 [CD]
 * @param pYC           Starting point of Segment 2
 * @param pXD           Ending point of Segment 2
 * @param pYD           Ending point of Segment 2
 * @param pIntersection Intersection point as output; can be null
 * @return true if the segments intersectParameters are typed as double for overflow and precision reasons.
 */
public static boolean intersection(final double pXA,final double pYA,final double pXB,final double pYB,final double pXC,final double pYC,final double pXD,final double pYD,final PointL pIntersection){
  if (parallelSideEffect(pXA,pYA,pXB,pYB,pXC,pYC,pXD,pYD,pIntersection)) {
    return true;
  }
  if (divisionByZeroSideEffect(pXA,pYA,pXB,pYB,pXC,pYC,pXD,pYD,pIntersection)) {
    return true;
  }
  final double d=(pXA - pXB) * (pYC - pYD) - (pYA - pYB) * (pXC - pXD);
  if (d == 0) {
    return false;
  }
  final double xi=((pXC - pXD) * (pXA * pYB - pYA * pXB) - (pXA - pXB) * (pXC * pYD - pYC * pXD)) / d;
  final double yi=((pYC - pYD) * (pXA * pYB - pYA * pXB) - (pYA - pYB) * (pXC * pYD - pYC * pXD)) / d;
  return check(pXA,pYA,pXB,pYB,pXC,pYC,pXD,pYD,pIntersection,xi,yi);
}

/** 
 * Returns union of two envelopes. This method does not modify the specified envelopes, but may return one of them as a result.
 * @param envelope1 first envelope, or null
 * @param envelope2 second envelope, or null
 * @return union of two envelopes
 */
public static double[] union(double[] envelope1,double[] envelope2){
  if (envelope1 == null) {
    return envelope2;
  }
 else   if (envelope2 == null) {
    return envelope1;
  }
  double minX1=envelope1[MIN_X], maxX1=envelope1[MAX_X], minY1=envelope1[MIN_Y], maxY1=envelope1[MAX_Y];
  double minX2=envelope2[MIN_X], maxX2=envelope2[MAX_X], minY2=envelope2[MIN_Y], maxY2=envelope2[MAX_Y];
  boolean modified=false;
  if (minX1 > minX2) {
    minX1=minX2;
    modified=true;
  }
  if (maxX1 < maxX2) {
    maxX1=maxX2;
    modified=true;
  }
  if (minY1 > minY2) {
    minY1=minY2;
    modified=true;
  }
  if (maxY1 < maxY2) {
    maxY1=maxY2;
    modified=true;
  }
  return modified ? new double[]{minX1,maxX1,minY1,maxY1} : envelope1;
}

/** 
 * Given a set of reference points as coordinates in  {@code xReferences} and {@code yReferences}and an x-axis value, linearly interpolates between corresponding reference points to give a y-axis value.
 * @param x The x-axis value for which a y-axis value is needed.
 * @param xReferences x coordinates of reference points.
 * @param yReferences y coordinates of reference points.
 * @return The linearly interpolated y-axis value.
 */
private static Pair<Long,Long> linearlyInterpolate(long x,long[] xReferences,long[] yReferences){
  int previousReferenceIndex=Util.binarySearchFloor(xReferences,x,true,true);
  long xPreviousReference=xReferences[previousReferenceIndex];
  long yPreviousReference=yReferences[previousReferenceIndex];
  int nextReferenceIndex=previousReferenceIndex + 1;
  if (nextReferenceIndex == xReferences.length) {
    return Pair.create(xPreviousReference,yPreviousReference);
  }
 else {
    long xNextReference=xReferences[nextReferenceIndex];
    long yNextReference=yReferences[nextReferenceIndex];
    double proportion=xNextReference == xPreviousReference ? 0.0 : ((double)x - xPreviousReference) / (xNextReference - xPreviousReference);
    long y=(long)(proportion * (yNextReference - yPreviousReference)) + yPreviousReference;
    return Pair.create(x,y);
  }
}

/** 
 * Resolves a seek given the requested seek position, a  {@link SeekParameters} and two candidatesync points.
 * @param positionUs The requested seek position, in microseocnds.
 * @param seekParameters The {@link SeekParameters}.
 * @param firstSyncUs The first candidate seek point, in micrseconds.
 * @param secondSyncUs The second candidate seek point, in microseconds. May equal {@code firstSyncUs} if there's only one candidate.
 * @return The resolved seek position, in microseconds.
 */
public static long resolveSeekPositionUs(long positionUs,SeekParameters seekParameters,long firstSyncUs,long secondSyncUs){
  if (SeekParameters.EXACT.equals(seekParameters)) {
    return positionUs;
  }
  long minPositionUs=subtractWithOverflowDefault(positionUs,seekParameters.toleranceBeforeUs,Long.MIN_VALUE);
  long maxPositionUs=addWithOverflowDefault(positionUs,seekParameters.toleranceAfterUs,Long.MAX_VALUE);
  boolean firstSyncPositionValid=minPositionUs <= firstSyncUs && firstSyncUs <= maxPositionUs;
  boolean secondSyncPositionValid=minPositionUs <= secondSyncUs && secondSyncUs <= maxPositionUs;
  if (firstSyncPositionValid && secondSyncPositionValid) {
    if (Math.abs(firstSyncUs - positionUs) <= Math.abs(secondSyncUs - positionUs)) {
      return firstSyncUs;
    }
 else {
      return secondSyncUs;
    }
  }
 else   if (firstSyncPositionValid) {
    return firstSyncUs;
  }
 else   if (secondSyncPositionValid) {
    return secondSyncUs;
  }
 else {
    return minPositionUs;
  }
}

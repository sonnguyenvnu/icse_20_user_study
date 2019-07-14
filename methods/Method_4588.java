/** 
 * Builds a  {@link SeekMap} from the recently gathered Cues information.
 * @return The built {@link SeekMap}. The returned  {@link SeekMap} may be unseekable if cuesinformation was missing or incomplete.
 */
private SeekMap buildSeekMap(){
  if (segmentContentPosition == C.POSITION_UNSET || durationUs == C.TIME_UNSET || cueTimesUs == null || cueTimesUs.size() == 0 || cueClusterPositions == null || cueClusterPositions.size() != cueTimesUs.size()) {
    cueTimesUs=null;
    cueClusterPositions=null;
    return new SeekMap.Unseekable(durationUs);
  }
  int cuePointsSize=cueTimesUs.size();
  int[] sizes=new int[cuePointsSize];
  long[] offsets=new long[cuePointsSize];
  long[] durationsUs=new long[cuePointsSize];
  long[] timesUs=new long[cuePointsSize];
  for (int i=0; i < cuePointsSize; i++) {
    timesUs[i]=cueTimesUs.get(i);
    offsets[i]=segmentContentPosition + cueClusterPositions.get(i);
  }
  for (int i=0; i < cuePointsSize - 1; i++) {
    sizes[i]=(int)(offsets[i + 1] - offsets[i]);
    durationsUs[i]=timesUs[i + 1] - timesUs[i];
  }
  sizes[cuePointsSize - 1]=(int)(segmentContentPosition + segmentContentSize - offsets[cuePointsSize - 1]);
  durationsUs[cuePointsSize - 1]=durationUs - timesUs[cuePointsSize - 1];
  cueTimesUs=null;
  cueClusterPositions=null;
  return new ChunkIndex(sizes,offsets,durationsUs,timesUs);
}

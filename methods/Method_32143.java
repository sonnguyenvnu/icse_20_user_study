/** 
 * Gets the millisecond offset to subtract from local time to get UTC time. This offset can be used to undo adding the offset obtained by getOffset. <pre> millisLocal == millisUTC   + getOffset(millisUTC) millisUTC   == millisLocal - getOffsetFromLocal(millisLocal) </pre> NOTE: After calculating millisLocal, some error may be introduced. At offset transitions (due to DST or other historical changes), ranges of local times may map to different UTC times. <p> For overlaps (where the local time is ambiguous), this method returns the offset applicable before the gap. The effect of this is that any instant calculated using the offset from an overlap will be in "summer" time. <p> For gaps, this method returns the offset applicable before the gap, ie "winter" offset. However, the effect of this is that any instant calculated using the offset from a gap will be after the gap, in "summer" time. <p> For example, consider a zone with a gap from 01:00 to 01:59:<br /> Input: 00:00 (before gap) Output: Offset applicable before gap  DateTime: 00:00<br /> Input: 00:30 (before gap) Output: Offset applicable before gap  DateTime: 00:30<br /> Input: 01:00 (in gap)     Output: Offset applicable before gap  DateTime: 02:00<br /> Input: 01:30 (in gap)     Output: Offset applicable before gap  DateTime: 02:30<br /> Input: 02:00 (after gap)  Output: Offset applicable after gap   DateTime: 02:00<br /> Input: 02:30 (after gap)  Output: Offset applicable after gap   DateTime: 02:30<br /> <p> NOTE: Prior to v2.0, the DST overlap behaviour was not defined and varied by hemisphere. Prior to v1.5, the DST gap behaviour was also not defined. In v2.4, the documentation was clarified again.
 * @param instantLocal  the millisecond instant, relative to this time zone, to get the offset for
 * @return the millisecond offset to subtract from local time to get UTC time
 */
public int getOffsetFromLocal(long instantLocal){
  final int offsetLocal=getOffset(instantLocal);
  final long instantAdjusted=instantLocal - offsetLocal;
  final int offsetAdjusted=getOffset(instantAdjusted);
  if (offsetLocal != offsetAdjusted) {
    if ((offsetLocal - offsetAdjusted) < 0) {
      long nextLocal=nextTransition(instantAdjusted);
      if (nextLocal == (instantLocal - offsetLocal)) {
        nextLocal=Long.MAX_VALUE;
      }
      long nextAdjusted=nextTransition(instantLocal - offsetAdjusted);
      if (nextAdjusted == (instantLocal - offsetAdjusted)) {
        nextAdjusted=Long.MAX_VALUE;
      }
      if (nextLocal != nextAdjusted) {
        return offsetLocal;
      }
    }
  }
 else   if (offsetLocal >= 0) {
    long prev=previousTransition(instantAdjusted);
    if (prev < instantAdjusted) {
      int offsetPrev=getOffset(prev);
      int diff=offsetPrev - offsetLocal;
      if (instantAdjusted - prev <= diff) {
        return offsetPrev;
      }
    }
  }
  return offsetAdjusted;
}

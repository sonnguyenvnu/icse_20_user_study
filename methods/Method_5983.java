/** 
 * Returns the offset between the input of  {@link #adjustSampleTimestamp(long)} and its output.If  {@link #DO_NOT_OFFSET} was provided to the constructor, 0 is returned. If the timestampadjuster is yet not initialized,  {@link C#TIME_UNSET} is returned.
 * @return The offset between {@link #adjustSampleTimestamp(long)}'s input and output. {@link C#TIME_UNSET} if the adjuster is not yet initialized and 0 if timestamps should notbe offset.
 */
public long getTimestampOffsetUs(){
  return firstSampleTimestampUs == DO_NOT_OFFSET ? 0 : lastSampleTimestampUs == C.TIME_UNSET ? C.TIME_UNSET : timestampOffsetUs;
}

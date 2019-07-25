/** 
 * Clears all stats.
 */
public CommonStatsFlags clear(){
  flags=EnumSet.noneOf(Flag.class);
  types=null;
  groups=null;
  fieldDataFields=null;
  completionDataFields=null;
  includeSegmentFileSizes=false;
  return this;
}

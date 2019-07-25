/** 
 * Sets all flags to return all stats.
 */
public CommonStatsFlags all(){
  flags=EnumSet.allOf(Flag.class);
  types=null;
  groups=null;
  fieldDataFields=null;
  completionDataFields=null;
  includeSegmentFileSizes=false;
  return this;
}

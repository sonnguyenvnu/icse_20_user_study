/** 
 * Calculates a base id for a  {@link VisibilityOutput} based on the {@link Component} and thedepth in the View hierarchy.
 */
private static long calculateVisibilityOutputBaseId(VisibilityOutput visibilityOutput,int level){
  if (level < 0 || level > MAX_LEVEL) {
    throw new IllegalArgumentException("Level must be non-negative and no greater than " + MAX_LEVEL + " actual level " + level);
  }
  final long componentId=visibilityOutput.getComponent() != null ? visibilityOutput.getComponent().getTypeId() : 0L;
  final long componentShifted=componentId << COMPONENT_ID_SHIFT;
  final long levelShifted=((long)level) << LEVEL_SHIFT;
  return 0L | componentShifted | levelShifted;
}

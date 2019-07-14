/** 
 * Like  {@link #offsetPx}, but the relative offset is based on a percentage of the mount content height
 */
public static DimensionValue heightPercentageOffset(float value){
  return new DimensionValue(Type.OFFSET_HEIGHT_PERCENTAGE,value);
}

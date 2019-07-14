/** 
 * Like  {@link #offsetPx}, but the relative offset is based on a percentage of the mount content width.
 */
public static DimensionValue widthPercentageOffset(float value){
  return new DimensionValue(Type.OFFSET_WIDTH_PERCENTAGE,value);
}

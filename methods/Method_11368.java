/** 
 * Set the minimum scale type. See static fields. Normally  {@link #SCALE_TYPE_CENTER_INSIDE} is best, for image galleries.
 */
public final void setMinimumScaleType(int scaleType){
  if (!VALID_SCALE_TYPES.contains(scaleType)) {
    throw new IllegalArgumentException("Invalid scale type: " + scaleType);
  }
  this.minimumScaleType=scaleType;
  if (isReady()) {
    fitToBounds(true);
    invalidate();
  }
}

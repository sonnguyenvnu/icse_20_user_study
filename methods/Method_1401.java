/** 
 * A helper function to allow dropping in BetterImageSpan as a replacement to ImageSpan, and allowing for center alignment if passed in.
 */
public static final @BetterImageSpanAlignment int normalizeAlignment(int alignment){
switch (alignment) {
case DynamicDrawableSpan.ALIGN_BOTTOM:
    return ALIGN_BOTTOM;
case ALIGN_CENTER:
  return ALIGN_CENTER;
case DynamicDrawableSpan.ALIGN_BASELINE:
default :
return ALIGN_BASELINE;
}
}

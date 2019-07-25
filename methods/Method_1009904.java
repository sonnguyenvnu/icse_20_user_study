/** 
 * Returns a possibly new FormatOptions instance possibly containing a subset of the formatting information. This is useful if a backend implementation wishes to create formatting options that ignore some of the specified formatting information.
 * @param allowedFlags A mask of flag values to be retained in the returned instance. Use{@link #ALL_FLAGS} to retain all flag values, or {@code 0} to suppress all flags.
 * @param allowWidth specifies whether to include width in the returned instance.
 * @param allowPrecision specifies whether to include precision in the returned instance.
 */
public FormatOptions filter(int allowedFlags,boolean allowWidth,boolean allowPrecision){
  if (isDefault()) {
    return this;
  }
  int newFlags=allowedFlags & flags;
  int newWidth=allowWidth ? width : UNSET;
  int newPrecision=allowPrecision ? precision : UNSET;
  if (newFlags == 0 && newWidth == UNSET && newPrecision == UNSET) {
    return DEFAULT;
  }
  if (newFlags == flags && newWidth == width && newPrecision == precision) {
    return this;
  }
  return new FormatOptions(newFlags,newWidth,newPrecision);
}

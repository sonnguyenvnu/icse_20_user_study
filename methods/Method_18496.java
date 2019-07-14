/** 
 * Resolve a size spec given a preferred size.
 * @param sizeSpec The spec to resolve.
 * @param preferredSize The preferred size.
 * @return The resolved size.
 */
public static int resolveSize(int sizeSpec,int preferredSize){
switch (SizeSpec.getMode(sizeSpec)) {
case EXACTLY:
    return SizeSpec.getSize(sizeSpec);
case AT_MOST:
  return Math.min(SizeSpec.getSize(sizeSpec),preferredSize);
case UNSPECIFIED:
return preferredSize;
default :
throw new IllegalStateException("Unexpected size mode: " + SizeSpec.getMode(sizeSpec));
}
}

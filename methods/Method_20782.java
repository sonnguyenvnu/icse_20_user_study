/** 
 * Return the corresponding tab position for a given sort param.
 */
public static int positionFromSort(final @Nullable DiscoveryParams.Sort sort){
  if (sort == null) {
    return 0;
  }
switch (sort) {
case HOME:
    return 0;
case POPULAR:
  return 1;
case NEWEST:
return 2;
case ENDING_SOON:
return 3;
default :
return 0;
}
}

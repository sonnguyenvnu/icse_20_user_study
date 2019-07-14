/** 
 * @return true if the measure specs we are trying to measure this with cannot be used and we needto measure an item to get a size.
 */
static final boolean shouldMeasureItemForSize(int widthSpec,int heightSpec,int scrollDirection,boolean canRemeasure){
  final boolean canUseSizeSpec=scrollDirection == VERTICAL ? SizeSpec.getMode(widthSpec) == SizeSpec.EXACTLY : SizeSpec.getMode(heightSpec) == SizeSpec.EXACTLY;
  return !canUseSizeSpec && canRemeasure;
}

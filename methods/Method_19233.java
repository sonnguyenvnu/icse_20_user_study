/** 
 * @param heightSpec the heightSpec used to measure the parent {@link RecyclerSpec}.
 * @return heightSpec of a child that is of span size 1
 */
@Override public int getChildHeightSpec(int heightSpec,RenderInfo renderInfo){
switch (mGridLayoutManager.getOrientation()) {
case GridLayoutManager.HORIZONTAL:
    Integer overrideHeight=(Integer)renderInfo.getCustomAttribute(GridLayoutInfo.OVERRIDE_SIZE);
  if (overrideHeight != null) {
    return SizeSpec.makeSizeSpec(overrideHeight,EXACTLY);
  }
if (renderInfo.isFullSpan()) {
  return SizeSpec.makeSizeSpec(SizeSpec.getSize(heightSpec),EXACTLY);
}
final int spanCount=mGridLayoutManager.getSpanCount();
final int spanSize=renderInfo.getSpanSize();
return SizeSpec.makeSizeSpec(spanSize * (SizeSpec.getSize(heightSpec) / spanCount),EXACTLY);
default :
return SizeSpec.makeSizeSpec(0,UNSPECIFIED);
}
}

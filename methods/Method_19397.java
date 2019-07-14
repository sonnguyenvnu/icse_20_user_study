/** 
 * @param heightSpec the heightSpec used to measure the parent {@link RecyclerSpec}.
 * @return heightSpec of a child that is of span size 1
 */
@Override public int getChildHeightSpec(int heightSpec,RenderInfo renderInfo){
switch (mStaggeredGridLayoutManager.getOrientation()) {
case StaggeredGridLayoutManager.HORIZONTAL:
    Integer overrideHeight=(Integer)renderInfo.getCustomAttribute(OVERRIDE_SIZE);
  if (overrideHeight != null) {
    return SizeSpec.makeSizeSpec(overrideHeight,EXACTLY);
  }
final int spanCount=mStaggeredGridLayoutManager.getSpanCount();
final int spanSize=renderInfo.isFullSpan() ? mStaggeredGridLayoutManager.getSpanCount() : 1;
return SizeSpec.makeSizeSpec(spanSize * (SizeSpec.getSize(heightSpec) / spanCount),EXACTLY);
default :
return SizeSpec.makeSizeSpec(0,UNSPECIFIED);
}
}

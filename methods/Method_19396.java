/** 
 * @param widthSpec the widthSpec used to measure the parent {@link RecyclerSpec}.
 * @return widthSpec of a child that is of span size 1
 */
@Override public int getChildWidthSpec(int widthSpec,RenderInfo renderInfo){
switch (mStaggeredGridLayoutManager.getOrientation()) {
case StaggeredGridLayoutManager.HORIZONTAL:
    return SizeSpec.makeSizeSpec(0,UNSPECIFIED);
default :
  Integer overrideWidth=(Integer)renderInfo.getCustomAttribute(OVERRIDE_SIZE);
if (overrideWidth != null) {
  return SizeSpec.makeSizeSpec(overrideWidth,EXACTLY);
}
final int spanCount=mStaggeredGridLayoutManager.getSpanCount();
final int spanSize=renderInfo.isFullSpan() ? mStaggeredGridLayoutManager.getSpanCount() : 1;
return SizeSpec.makeSizeSpec(spanSize * ((SizeSpec.getSize(widthSpec)) / spanCount),EXACTLY);
}
}

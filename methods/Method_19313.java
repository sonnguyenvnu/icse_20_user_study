@VisibleForTesting @GuardedBy("this") int computeLayoutsToFillListViewport(List<ComponentTreeHolder> holders,int offset,int maxWidth,int maxHeight,@Nullable Size outputSize){
  final LayoutInfo.ViewportFiller filler=mLayoutInfo.createViewportFiller(maxWidth,maxHeight);
  if (filler == null) {
    return 0;
  }
  ComponentsSystrace.beginSection("computeLayoutsToFillListViewport");
  final int widthSpec=SizeSpec.makeSizeSpec(maxWidth,SizeSpec.EXACTLY);
  final int heightSpec=SizeSpec.makeSizeSpec(maxHeight,SizeSpec.EXACTLY);
  final Size outSize=new Size();
  int numInserted=0;
  int index=offset;
  while (filler.wantsMore() && index < holders.size()) {
    final ComponentTreeHolder holder=holders.get(index);
    final RenderInfo renderInfo=holder.getRenderInfo();
    if (renderInfo.rendersView()) {
      break;
    }
    holder.computeLayoutSync(mComponentContext,mLayoutInfo.getChildWidthSpec(widthSpec,renderInfo),mLayoutInfo.getChildHeightSpec(heightSpec,renderInfo),outSize);
    filler.add(renderInfo,outSize.width,outSize.height);
    index++;
    numInserted++;
  }
  if (outputSize != null) {
    final int fill=filler.getFill();
    if (mLayoutInfo.getScrollDirection() == VERTICAL) {
      outputSize.width=maxWidth;
      outputSize.height=Math.min(fill,maxHeight);
    }
 else {
      outputSize.width=Math.min(fill,maxWidth);
      outputSize.height=maxHeight;
    }
  }
  ComponentsSystrace.endSection();
  logFillViewportInserted(numInserted,holders.size());
  return numInserted;
}

@OnBoundsDefined static void onBoundsDefined(ComponentContext context,ComponentLayout layout,@Prop Component contentProps,@Prop(optional=true) boolean fillViewport,@State ComponentTree childComponentTree,@FromMeasure Integer measuredComponentWidth,@FromMeasure Integer measuredComponentHeight,Output<Integer> componentWidth,Output<Integer> componentHeight,Output<YogaDirection> layoutDirection){
  final int layoutWidth=layout.getWidth() - layout.getPaddingLeft() - layout.getPaddingRight();
  if (measuredComponentWidth != null && measuredComponentHeight != null) {
    componentWidth.set(Math.max(measuredComponentWidth,fillViewport ? layoutWidth : 0));
    componentHeight.set(measuredComponentHeight);
  }
 else {
    final int measuredWidth;
    final int measuredHeight;
    Size contentSize=new Size();
    childComponentTree.setRootAndSizeSpec(contentProps,SizeSpec.makeSizeSpec(0,UNSPECIFIED),SizeSpec.makeSizeSpec(layout.getHeight(),EXACTLY),contentSize);
    measuredWidth=Math.max(contentSize.width,fillViewport ? layoutWidth : 0);
    measuredHeight=contentSize.height;
    componentWidth.set(measuredWidth);
    componentHeight.set(measuredHeight);
  }
  layoutDirection.set(layout.getResolvedLayoutDirection());
}

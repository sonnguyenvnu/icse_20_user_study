@OnBoundsDefined static void onBoundsDefined(ComponentContext c,ComponentLayout layout,@Prop Component childComponent,@Prop(optional=true) boolean fillViewport,@State ComponentTree childComponentTree,@FromMeasure Integer measuredWidth,@FromMeasure Integer measuredHeight){
  final int layoutWidth=layout.getWidth() - layout.getPaddingLeft() - layout.getPaddingRight();
  final int layoutHeight=layout.getHeight() - layout.getPaddingTop() - layout.getPaddingBottom();
  if (measuredWidth != null && measuredWidth == layoutWidth && (!fillViewport || (measuredHeight != null && measuredHeight == layoutHeight))) {
    return;
  }
  measureVerticalScroll(c,SizeSpec.makeSizeSpec(layout.getWidth(),EXACTLY),SizeSpec.makeSizeSpec(layout.getHeight(),EXACTLY),new Size(),childComponentTree,childComponent,fillViewport);
}

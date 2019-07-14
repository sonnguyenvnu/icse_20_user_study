@OnMeasure static void onMeasure(ComponentContext context,ComponentLayout layout,int widthSpec,int heightSpec,Size size,@Prop Component contentProps,@State ComponentTree childComponentTree,Output<Integer> measuredComponentWidth,Output<Integer> measuredComponentHeight){
  final int measuredWidth;
  final int measuredHeight;
  final Size contentSize=new Size();
  childComponentTree.setRootAndSizeSpec(contentProps,SizeSpec.makeSizeSpec(0,UNSPECIFIED),heightSpec,contentSize);
  contentProps.measure(context,SizeSpec.makeSizeSpec(0,UNSPECIFIED),heightSpec,contentSize);
  measuredWidth=contentSize.width;
  measuredHeight=contentSize.height;
  measuredComponentWidth.set(measuredWidth);
  measuredComponentHeight.set(measuredHeight);
  size.width=SizeSpec.getMode(widthSpec) == UNSPECIFIED ? measuredWidth : SizeSpec.getSize(widthSpec);
  size.height=measuredHeight;
}

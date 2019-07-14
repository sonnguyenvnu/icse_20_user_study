static void measureVerticalScroll(ComponentContext c,int widthSpec,int heightSpec,Size size,ComponentTree childComponentTree,Component childComponent,boolean fillViewport){
  if (fillViewport) {
    childComponent=Wrapper.create(c).delegate(childComponent).minHeightPx(SizeSpec.getSize(heightSpec)).build();
  }
  childComponentTree.setRootAndSizeSpec(childComponent,widthSpec,SizeSpec.makeSizeSpec(0,UNSPECIFIED),size);
switch (SizeSpec.getMode(heightSpec)) {
case EXACTLY:
    size.height=SizeSpec.getSize(heightSpec);
  break;
case AT_MOST:
size.height=Math.min(SizeSpec.getSize(heightSpec),size.height);
break;
}
}

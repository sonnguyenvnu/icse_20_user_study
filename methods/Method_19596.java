private static Component.Builder buildDragHandle(ComponentContext c){
  final int radiusDip=HANDLE_SIZE_DP / 2;
  return Text.create(c).widthDip(HANDLE_SIZE_DP).heightDip(HANDLE_SIZE_DP).background(buildCircleDrawable(c,0xFFDDDDDD,radiusDip)).border(Border.create(c).color(YogaEdge.ALL,Color.BLACK).widthDip(YogaEdge.ALL,1).radiusDip(radiusDip).build()).verticalGravity(VerticalGravity.CENTER).textAlignment(Layout.Alignment.ALIGN_CENTER).textSizeDip(16).typeface(Typeface.DEFAULT_BOLD).text("DRAG");
}

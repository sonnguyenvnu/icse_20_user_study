@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop Integer[] colors,@Prop RecyclerCollectionEventsController eventsController,@State int snapMode){
  final RecyclerConfiguration recyclerConfiguration=new ListRecyclerConfiguration(LinearLayoutManager.HORIZONTAL,false,snapMode);
  return Column.create(c).paddingDip(YogaEdge.ALL,5).child(Row.create(c).alignContent(YogaAlign.STRETCH).marginDip(YogaEdge.TOP,10).child(Text.create(c).alignSelf(YogaAlign.CENTER).flexGrow(2f).text("Snap type: ").textSizeSp(20)).child(Spinner.create(c).flexGrow(1.f).options(Arrays.asList(SNAP_MODE_STRING)).selectedOption(getSnapModeString(snapMode)).itemSelectedEventHandler(HorizontalScrollWithSnapComponent.onSnapModeSelected(c)))).child(RecyclerCollectionComponent.create(c).key("snapMode" + snapMode).disablePTR(true).recyclerConfiguration(recyclerConfiguration).section(DataDiffSection.<Integer>create(new SectionContext(c)).data(Arrays.asList(colors)).renderEventHandler(HorizontalScrollWithSnapComponent.onRender(c)).build()).canMeasureRecycler(true).itemDecoration(new RecyclerView.ItemDecoration(){
    @Override public void getItemOffsets(    Rect outRect,    View view,    RecyclerView parent,    RecyclerView.State state){
      super.getItemOffsets(outRect,view,parent,state);
      int spacingPx=40;
      int exteriorSpacingPx=0;
      int startPx=spacingPx;
      int endPx=0;
      int position=parent.getChildLayoutPosition(view);
      if (position == 0) {
        startPx=exteriorSpacingPx;
      }
      if (position == state.getItemCount() - 1) {
        endPx=exteriorSpacingPx;
      }
      outRect.left=startPx;
      outRect.right=endPx;
    }
  }
).eventsController(eventsController)).build();
}

@OnEvent(ClickEvent.class) static void onClick(ComponentContext c,@Prop RecyclerCollectionEventsController eventsController,@Param boolean forward){
  if (forward) {
    eventsController.requestScrollToNextPosition(true);
  }
 else {
    eventsController.requestScrollToPreviousPosition(true);
  }
}

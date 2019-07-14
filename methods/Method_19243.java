@OnUnmount static void onUnmount(ComponentContext context,HorizontalScrollLithoView mountedView,@Prop(optional=true) HorizontalScrollEventsController eventsController){
  mountedView.unmount();
  if (eventsController != null) {
    eventsController.setScrollableView(null);
  }
}

@OnTrigger(SetSelectionEvent.class) static void setSelection(ComponentContext c,@State AtomicReference<EditTextWithEventHandlers> mountedView,@FromTrigger int start,@FromTrigger int end){
  EditTextWithEventHandlers view=mountedView.get();
  if (view != null) {
    view.setSelection(start,end < start ? start : end);
  }
}

@Override public void receive(Event event){
  if (event instanceof ItemStatePredictedEvent) {
    ItemStatePredictedEvent prediction=(ItemStatePredictedEvent)event;
    Item item=itemUIRegistry.get(prediction.getItemName());
    if (item instanceof GroupItem) {
      return;
    }
    for (    PageChangeListener pageChangeListener : pageChangeListeners.values()) {
      if (prediction.isConfirmation()) {
        pageChangeListener.keepCurrentState(item);
      }
 else {
        pageChangeListener.changeStateTo(item,prediction.getPredictedState());
      }
    }
  }
}

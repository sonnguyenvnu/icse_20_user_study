private void bindTriggerHandler(Component component){
synchronized (mEventTriggersContainer) {
    component.recordEventTrigger(mEventTriggersContainer);
  }
}

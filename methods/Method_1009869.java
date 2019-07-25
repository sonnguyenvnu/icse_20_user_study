/** 
 * Responsible for posting Sponge events to Forge.
 * @param eventData The event data from Sponge
 * @return true if cancelled, false if not
 */
private SpongeToForgeEventData post(SpongeToForgeEventData eventData){
  final Event spongeEvent=eventData.getSpongeEvent();
  final boolean hasSpongeListeners=!eventData.getSpongeListenerCache().getListeners().isEmpty();
  if (hasSpongeListeners) {
    for (    Order order : Order.values()) {
      post(spongeEvent,eventData.getSpongeListenerCache().getListenersByOrder(order),true,false,eventData.useCauseStackManager());
    }
  }
  SpongeToForgeEventFactory.createAndPostForgeEvent(eventData);
  if (hasSpongeListeners) {
    SpongeToForgeEventFactory.handlePrefireLogic(spongeEvent);
    for (    Order order : Order.values()) {
      post(spongeEvent,eventData.getSpongeListenerCache().getListenersByOrder(order),false,false,eventData.useCauseStackManager());
    }
  }
  return eventData;
}

/** 
 * refresh all sensors on this bridge
 */
private void refresh(){
  if (refreshable) {
    long now=System.currentTimeMillis();
    List<Thing> thingList=getThing().getThings();
    int thingCount=thingList.size();
    Iterator<Thing> childListIterator=thingList.iterator();
    logger.trace("refreshTask starts at {}, {} childs",now,thingCount);
    while (childListIterator.hasNext() && refreshable) {
      Thing owThing=childListIterator.next();
      logger.trace("refresh: getting handler for {} ({} to go)",owThing.getUID(),thingCount);
      OwBaseThingHandler owHandler=(OwBaseThingHandler)owThing.getHandler();
      if (owHandler != null) {
        if (owHandler.isRefreshable()) {
          logger.trace("{} initialized, refreshing",owThing.getUID());
          owHandler.refresh(OwBaseBridgeHandler.this,now);
        }
 else {
          logger.trace("{} not initialized, skipping refresh",owThing.getUID());
        }
      }
 else {
        logger.debug("{} handler missing",owThing.getUID());
      }
      thingCount--;
    }
    refreshBridgeChannels(now);
    Thing updateThing=thingPropertiesUpdateQueue.poll();
    if (updateThing != null) {
      logger.trace("update: getting handler for {} ({} total in list)",updateThing.getUID(),thingPropertiesUpdateQueue.size());
      OwBaseThingHandler owHandler=(OwBaseThingHandler)updateThing.getHandler();
      if (owHandler != null) {
        try {
          Map<String,String> properties=new HashMap<String,String>();
          properties.putAll(updateThing.getProperties());
          properties.putAll(owHandler.updateSensorProperties(this));
          updateThing.setProperties(properties);
          owHandler.initialize();
          logger.debug("{} sucessfully updated properties, removing from property update list",updateThing.getUID());
        }
 catch (        OwException e) {
          thingPropertiesUpdateQueue.add(updateThing);
          logger.debug("updating thing properties for {} failed: {}, adding to end of list",updateThing.getUID(),e.getMessage());
        }
      }
 else {
        logger.debug("{} is missing handler, removing from property update list",updateThing.getUID());
      }
    }
  }
}

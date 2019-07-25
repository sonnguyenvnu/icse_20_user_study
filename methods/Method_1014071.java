@Override public void receive(Event event){
  if (event instanceof ThingStatusInfoChangedEvent) {
    ThingStatusInfoChangedEvent changedEvent=(ThingStatusInfoChangedEvent)event;
    if (changedEvent.getStatusInfo().getStatus() != ThingStatus.ONLINE) {
      return;
    }
    ThingUID thingUID=changedEvent.getThingUID();
    FirmwareUpdateHandler firmwareUpdateHandler=getFirmwareUpdateHandler(thingUID);
    if (firmwareUpdateHandler != null && !firmwareStatusInfoMap.containsKey(thingUID)) {
      initializeFirmwareStatus(firmwareUpdateHandler);
    }
  }
}

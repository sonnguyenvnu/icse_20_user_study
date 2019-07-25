private void unsubscribe(){
  for (  String eventName : this.subscribedEvents) {
    connManager.getDigitalSTROMAPI().unsubscribeEvent(this.connManager.getSessionToken(),eventName,this.subscriptionID,config.getConnectionTimeout(),config.getReadTimeout());
  }
}

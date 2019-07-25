@Override public void initialize(){
  logger.debug("initializing handler for thing {}",getThing().getUID());
  if (migrateThingType()) {
    return;
  }
  if (getUDN() != null) {
    service.registerParticipant(this);
    onUpdate();
    this.notificationTimeout=getConfigAs(ZonePlayerConfiguration.class).notificationTimeout;
    if (this.notificationTimeout == null) {
      this.notificationTimeout=DEFAULT_NOTIFICATION_TIMEOUT;
    }
  }
 else {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"@text/offline.conf-error-missing-udn");
    logger.debug("Cannot initalize the zoneplayer. UDN not set.");
  }
}

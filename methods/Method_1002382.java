/** 
 * When resetInterval set to true, reset the interval time to Update Interval time. Otherwise reuse the existing interval time
 * @param resetInterval:
 */
public void reset(boolean resetInterval){
  _quarantineState=QuarantineStates.FAILURE;
  if (resetInterval) {
    _timeTilNextCheck=_config.getUpdateIntervalMs();
  }
 else {
    _log.warn("HealthCheck: Interval {}ms is not reset for client {}, because it is quarantined again within 30s. " + "This can happen if current health checking method is not sufficient for capturing when a node should stay in quarantine, " + "for example it returns fast but the real queries return slow.",_timeTilNextCheck,_trackerClient.getUri());
  }
}

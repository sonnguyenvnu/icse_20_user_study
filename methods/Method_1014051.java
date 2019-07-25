/** 
 * Builds and returns the status info.
 * @return status info
 */
public ThingStatusInfo build(){
  return new ThingStatusInfo(status,statusDetail,description);
}

/** 
 * Creates a status info builder for the given status and detail.
 * @param status the status (must not be null)
 * @param statusDetail the detail of the status (must not be null)
 * @return status info builder
 */
public static ThingStatusInfoBuilder create(ThingStatus status,ThingStatusDetail statusDetail){
  return new ThingStatusInfoBuilder(status,statusDetail,null);
}

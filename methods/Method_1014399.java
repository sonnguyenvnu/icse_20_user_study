/** 
 * sets (or replaces) implementation details for a given bridge
 * @param thingTypeUID the bridges thing type UID
 * @param owDeviceParameter the information for this bridge
 */
public void set(ThingTypeUID thingTypeUID,OwDeviceParameter owDeviceParameter){
  map.put(thingTypeUID,owDeviceParameter);
}

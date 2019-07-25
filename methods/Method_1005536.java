/** 
 * @param geofencingProvider geofencing provider we want to use
 * @return request handler for geofencing operations
 */
public GeofencingControl geofencing(GeofencingProvider geofencingProvider){
  return new GeofencingControl(this,geofencingProvider);
}

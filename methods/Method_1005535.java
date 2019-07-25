/** 
 * @return request handler for geofencing operations
 */
public GeofencingControl geofencing(){
  return geofencing(new GeofencingGooglePlayServicesProvider());
}
